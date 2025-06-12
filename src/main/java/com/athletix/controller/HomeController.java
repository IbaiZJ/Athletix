package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.athletix.model.DTO.TrackingCardDTO;
import com.athletix.model.Trackings;
import com.athletix.model.Users;
import com.athletix.service.TrackingService;
import com.athletix.service.UserService;
import com.athletix.util.TrackingUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private final TrackingService trackingService;
    private final UserService userService;

    public HomeController(
            TrackingService trackingService,
            UserService userService) {
        this.trackingService = trackingService;
        this.userService = userService;
        log.info("HomeController initialized");
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Users user = userService.getCurrentUser();

        Trackings lastTracking = trackingService.getLastTracking(user);
        TrackingCardDTO trackingCard = TrackingUtil.toTrackingCardDTO(lastTracking, null);

        model.addAttribute("tracking", trackingCard);
        
        log.info("Home page accessed");
        return "pages/home";
    }

}
