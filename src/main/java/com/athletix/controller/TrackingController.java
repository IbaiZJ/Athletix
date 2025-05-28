package com.athletix.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.enums.NotificationEnum;
import com.athletix.model.DTO.NotificationRequestDTO;
import com.athletix.model.DTO.TrackingCardDTO;
import com.athletix.model.DTO.TrackingRegistrationDTO;
import com.athletix.model.DTO.TrackingStatisticsDTO;
import com.athletix.model.Trackings;
import com.athletix.model.Users;
import com.athletix.service.NotificationService;
import com.athletix.service.TrackingService;
import com.athletix.service.UserService;
import com.athletix.util.TrackingUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tracking")
public class TrackingController {
    private static final Logger log = LoggerFactory.getLogger(TrackingController.class);

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
        log.info("TrackingController initialized");
    }

    @GetMapping("")
    public String showMyTrackings() {
        Users user = userService.getCurrentUser();
        log.info("Showing trackings for user: {}", user.getUsername());

        return "redirect:/tracking/" + user.getUsername();
    }

    @GetMapping("/{username}")
    public String showUserTrackings(@PathVariable("username") String username, Model model) {
        // Check if user exists
        Users user = userService.findByUsername(username);
        if (user == null) {
            log.error("User not found: {}", username);
            return "error/404";
        }

        // Get trackings for the user
        List<Trackings> trackings = trackingService.getTrackingsByUser(user);
        List<TrackingCardDTO> trackingDTOs = new ArrayList<>();
        for (Trackings tracking : trackings) {
            // List<Images> images = imageService.findByTracking(tracking); // <-- asegúrate
            // de tener este método
            trackingDTOs.add(TrackingUtil.toTrackingCardDTO(tracking, null));
        }
        model.addAttribute("trackings", trackingDTOs);

        // Tracking statistics
        TrackingStatisticsDTO statistics = TrackingUtil.getStatistics(trackings);
        model.addAttribute("trackingStatistics", statistics);

        log.info("Trackings for user {}: {}", username, trackings);

        return "pages/tracking";
    }

    @GetMapping("/{username}/create")
    @PreAuthorize("#username == authentication.name")
    public String createTrackingForm(@PathVariable("username") String username) {
        log.info("Create tracking page accessed");
        return "pages/tracking/trackingForm";
    }

    @PostMapping("/{username}/create")
    @PreAuthorize("#username == authentication.name")
    public String createTracking(@PathVariable("username") String username, TrackingRegistrationDTO tracking,
            HttpServletRequest request) {

        Users user = userService.getCurrentUser();
        log.info("Creating new tracking for user: {}", user.getUsername());

        // Create tracking
        trackingService.createTracking(user, tracking);
        log.info("Tracking created for user: {}", user.getUsername());

        // Create notification
        NotificationRequestDTO notification = new NotificationRequestDTO("New activity created",
                "You have created a new activity: " + tracking.getTitle(), NotificationEnum.CREATE_TRACKING);
        notificationService.createNotificationForUser(user, notification);
        notificationService.reloadNotifications(request, user);

        log.info("Creating new tracking with title: {}", tracking.getTitle());

        return "redirect:/tracking/" + username;
    }

}
