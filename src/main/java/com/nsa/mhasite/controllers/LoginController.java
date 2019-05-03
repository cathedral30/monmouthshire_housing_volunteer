package com.nsa.mhasite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @RequestMapping(path = "login", method = RequestMethod.GET)
    public RedirectView returnHome(){
        return new RedirectView("");
    }
}
