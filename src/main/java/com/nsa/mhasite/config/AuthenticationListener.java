package com.nsa.mhasite.config;

import com.nsa.mhasite.domain.LockedUser;
import com.nsa.mhasite.domain.User;
import com.nsa.mhasite.repositories.LockedUserRepository;
import com.nsa.mhasite.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Component
public class AuthenticationListener {

    static final Logger LOG = LoggerFactory.getLogger(AuthenticationListener.class);
    private UserRepository uRepo;
    private LockedUserRepository lRepo;
    private HttpServletRequest request;


    @Autowired
    public AuthenticationListener(UserRepository uRepo, LockedUserRepository lRepo, HttpServletRequest request) {
        this.uRepo = uRepo;
        this.lRepo = lRepo;
        this.request = request;
    }

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {

        String username = (String) event.getAuthentication().getPrincipal();
        LOG.info("Failed login with username: " + username);

        try {
            User user = uRepo.findUserByUsername(username).get();
            try {
                LockedUser lockedUser = lRepo.findByUsername(username);

                if (!lockedUser.getLocked()) {
                    if (lockedUser.getAttempts() > 3) {
                        Timestamp time = new Timestamp(System.currentTimeMillis());
                        time.setTime(time.getTime() + TimeUnit.MINUTES.toMillis(30));
                        lRepo.setLocked(username, time);
                    } else {
                        lRepo.setAttempts(username, lockedUser.getAttempts() + 1);
                    }
                } else {
                    LOG.info("Login attempted to locked account: " + username);
                }
            } catch (EmptyResultDataAccessException z) {
                lRepo.createLockedUser(username);
            }
        } catch (EmptyResultDataAccessException e) {
            LOG.info("Login failed with incorrect username: " + username);
        }
    }

    @EventListener
    public void authenticationSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        try {
            LockedUser lockedUser = lRepo.findByUsername(username);
            if (lockedUser.getLocked()) {
                LOG.info("Account locked");
            } else {
                LOG.info("Account not locked");
                lRepo.deleteLockedUser(username);
            }
        } catch (EmptyResultDataAccessException e) {
            LOG.info("Account not locked");
        }
    }
}
