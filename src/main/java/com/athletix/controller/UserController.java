package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.athletix.model.User;
import com.athletix.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String showCreateAccountForm() {
        log.info("Create Account page accessed");
        return "pages/createAccount";
    }

    @PostMapping("/create")
    public String createAccount(@ModelAttribute("user") User user, @RequestParam(required = false) boolean valid) {
        try {
            userService.registerUser(user);
            log.info("Account registed for user: {}", user.getUsername());
            return "redirect:/login?registered";
        } catch (IllegalArgumentException e) {
            // model.addAttribute("error", e.getMessage());
            log.error("Error creating account: {}", e.getMessage());
            return "redirect:/user/create?error=" + e.getMessage();
        }
    }
}
