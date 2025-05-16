package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.athletix.model.User;
import com.athletix.service.UserService;

@Controller
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Look if user is already authenticated
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            log.info("User is already authenticated, redirecting to /home");
            return "redirect:/home";
        }

        log.info("Login page accessed");
        return "pages/login";
    }

    @GetMapping("/create-account")
    public String showCreateAccountForm() {
        log.info("Create Account page accessed");
        return "pages/createAccount";
    }

    @PostMapping("/create-account")
    public String createAccount(@ModelAttribute("user") User user, @RequestParam(required = false) boolean valid) {
        try {
            userService.registerUser(user.getUsername(), user.getPassword());
            log.info("Account registed for user: {}", user.getUsername());
            return "redirect:/login?registered";
        } catch (IllegalArgumentException e) {
            // model.addAttribute("error", e.getMessage());
            log.error("Error creating account: {}", e.getMessage());
            return "redirect:/create-account?error=" + e.getMessage();
        }
    }

}
