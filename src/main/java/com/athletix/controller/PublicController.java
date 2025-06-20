package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {
    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    @GetMapping("/")
    public String index() {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Look if user is already authenticated
        // if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
        //     log.info("User is already authenticated, redirecting to /home");
        //     return "redirect:/home";
        // }

        log.info("Index page accessed");
        return "pages/index";
    }

    @GetMapping("/about")
    public String about() {
        log.info("About page accessed");
        return "pages/about";
    }

    @GetMapping("/contact")
    public String contact() {
        log.info("Contact page accessed");
        return "pages/contact";
    }

    @GetMapping("/services")
    public String services() {
        log.info("Services page accessed");
        return "pages/services";
    }

}
