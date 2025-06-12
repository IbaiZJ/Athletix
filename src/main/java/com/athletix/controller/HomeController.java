package com.athletix.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.athletix.model.DTO.EventDTO;
import com.athletix.model.DTO.TrackingCardDTO;
import com.athletix.model.Trackings;
import com.athletix.model.Users;
import com.athletix.service.EventService;
import com.athletix.service.TrackingService;
import com.athletix.service.UserService;
import com.athletix.util.TrackingUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private final TrackingService trackingService;
    private final UserService userService;
    private final EventService eventService;

    public HomeController(
            TrackingService trackingService,
            UserService userService,
            EventService eventService) {
        this.trackingService = trackingService;
        this.userService = userService;
        this.eventService = eventService;
        log.info("HomeController initialized");
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Users user = userService.getCurrentUser();

        Trackings lastTracking = trackingService.getLastTracking(user);
        if(lastTracking != null) {
            TrackingCardDTO trackingCard = TrackingUtil.toTrackingCardDTO(lastTracking, null);
            model.addAttribute("tracking", trackingCard);
            log.info("Last tracking: {}", lastTracking.getTitle());
        } else {
            model.addAttribute("tracking", null);
        }


        List<EventDTO> registeredEvents = eventService.getRegisteredEvents(user);
        model.addAttribute("registeredEvents", registeredEvents);
        log.info("Save registered events in model");

        
        log.info("Home page accessed");
        return "pages/home";
    }

}
