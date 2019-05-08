package com.nsa.mhasite.services;

import com.nsa.mhasite.domain.LockedUser;
import com.nsa.mhasite.repositories.LockedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Service
public class LockedCheck {

    private HttpServletRequest request;
    private LockedUserRepository lRepo;

    @Autowired
    public LockedCheck(HttpServletRequest request, LockedUserRepository lRepo) {
        this.request = request;
        this.lRepo = lRepo;
    }

    public Boolean lockedCheck() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            try {
                LockedUser user = lRepo.findByUsername(username);
                if (user.getLocked()) {
                    try {
                        request.logout();
                        return Boolean.TRUE;
                    } catch(ServletException e) {
                        //
                    }
                }
            } catch(EmptyResultDataAccessException e) {
                //
            }
        }
        return Boolean.FALSE;
    }
}
