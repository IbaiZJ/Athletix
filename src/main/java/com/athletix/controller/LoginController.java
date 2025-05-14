package com.athletix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login() {
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            return "pages/about";
        }*/

        return "pages/login";
    }

    @GetMapping("/create-account")
    public String createAccount() {
        return "pages/createAccount";
    }
    
}
