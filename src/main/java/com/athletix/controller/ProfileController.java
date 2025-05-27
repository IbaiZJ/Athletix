package com.athletix.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.model.Users;
import com.athletix.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    private static final String PROFILE_USER = "profileUser";

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
        log.info("ProfileController initialized");
    }

    @GetMapping("")
    public String getProfiles() {
        log.info("Profile page accessed");
        return "/pages/profiles";
    }

    @GetMapping("/{username}")
    public String getUserProfile(@PathVariable("username") String username, Model model) {
        log.info("Profile page accessed for user: " + username);

        // Get user by username
        Optional<Users> userOptional = Optional.ofNullable(userService.findByUsername(username));

        // Check if user exists
        if (userOptional.isEmpty()) {
            log.error("User not found: {}", username);
            return "error/404";
        }

        // Save user to model
        Users user = userOptional.get();
        model.addAttribute(PROFILE_USER, user);
        log.info("Redirect to /profile/{}", username);

        return "pages/profile/userProfile";
    }
}
