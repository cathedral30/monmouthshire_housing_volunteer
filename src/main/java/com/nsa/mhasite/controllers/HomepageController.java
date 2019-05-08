package com.nsa.mhasite.controllers;

import com.nsa.mhasite.repositories.UserRoleRepository;
import com.nsa.mhasite.services.LockedCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomepageController {
    static final Logger LOG = LoggerFactory.getLogger(HomepageController.class);
    private UserRoleRepository uRepo;
    private LockedCheck check;

    @Autowired
    public HomepageController(UserRoleRepository uRepo, LockedCheck check) {
        this.uRepo = uRepo;
        this.check = check;
}

    @RequestMapping("")
    public String servePortal(Model model) {
        LOG.debug("Handling empty route after login");

        if (check.lockedCheck()) {
            return "locked";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            LOG.debug("User authenticated");
            String currentUserName = authentication.getName();
            LOG.debug(currentUserName);
            List<String> userRoles = uRepo.findRoleByUsername(currentUserName);
            model.addAttribute("roles", userRoles);
            LOG.debug("Serving portal page");
            return "portal";
        } else {
            return "login";
        }
    }
}
//
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//                String currentUserName = authentication.getName();
//                LOG.debug(currentUserName);
//                Optional<VolunteerUser> userFromSearch = this.vService.findVolunteerByUsername(currentUserName);
//
//        if (userFromSearch.isPresent()) {
//        LOG.debug("User is present: " + userFromSearch.toString());
//        model.addAttribute("user", userFromSearch.get());
//        return "userProfile";
//        }
//        }
