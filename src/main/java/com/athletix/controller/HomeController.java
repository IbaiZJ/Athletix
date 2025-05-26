package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/home")
    public String home(HttpSession session) {
        // if (session.getAttribute("user") == null) {
        //     return "pages/login";
        // }
        log.info("Home page accessed");
        return "pages/home";
    }

}
