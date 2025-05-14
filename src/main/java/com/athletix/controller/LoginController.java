package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    @GetMapping("/login")
    public String login() {
        /*
         * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         * 
         * if (auth != null && auth.isAuthenticated() &&
         * !auth.getPrincipal().equals("anonymousUser")) {
         * return "pages/about";
         * }
         */
        log.info("Login page accessed");
        return "pages/login";
    }

    @GetMapping("/create-account")
    public String createAccount() {
        log.info("Create Account page accessed");
        return "pages/createAccount";
    }

}
