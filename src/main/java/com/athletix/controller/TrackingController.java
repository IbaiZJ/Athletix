package com.athletix.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.enums.NotificationEnum;
import com.athletix.model.DTO.NotificationRequestDTO;
import com.athletix.model.DTO.TrackingRegistrationDTO;
import com.athletix.model.Notifications;
import com.athletix.model.Trackings;
import com.athletix.model.Users;
import com.athletix.service.NotificationService;
import com.athletix.service.TrackingService;
import com.athletix.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tracking")
public class TrackingController {
    private static final Logger log = LoggerFactory.getLogger(TrackingController.class);
    private static final String TRACKING_LIST = "trackings";

    private final NotificationService notificationService;
    private final TrackingService trackingService;
    private final UserService userService;

    public TrackingController(
            NotificationService notificationService,
            TrackingService trackingService,
            UserService userService) {
        this.notificationService = notificationService;
        this.trackingService = trackingService;
        this.userService = userService;
    }

    @GetMapping("")
    public String showMyTrackings(Model model) {
        Users user = userService.getCurrentUser();
        List<Trackings> trackings = trackingService.getTrackingsByUser(user);
        // TODO: DTO tracking
        model.addAttribute(TRACKING_LIST, trackings);
        log.info("Trackings for user {}: {}", user.getUsername(), trackings);

        log.info("Tracking page accessed");
        return "pages/tracking";
    }

    @GetMapping("/create")
    public String createTrackingForm() {
        log.info("Create tracking page accessed");
        return "pages/tracking/trackingForm";
    }
    
    @PostMapping("/create")
    public String createTracking(TrackingRegistrationDTO tracking, HttpServletRequest request) {
        log.info("Creating new tracking with title: {}", tracking.getTitle());

        trackingService.createTracking(userService.getCurrentUser(), tracking);
        Users user = userService.getCurrentUser();
        NotificationRequestDTO notification = new NotificationRequestDTO("New activity created",
                "You have created a new activity: " + tracking.getTitle(),
                NotificationEnum.CREATE_TRACKING);
        notificationService.createNotificationForUser(user, notification);

        List<Notifications> notifications = notificationService.getNotificationsByUser(user);
        HttpSession session = request.getSession(false);
        if (session != null && notifications != null) {
            session.setAttribute("notifications", notifications);
        }

        return "redirect:/tracking";
    }

}
