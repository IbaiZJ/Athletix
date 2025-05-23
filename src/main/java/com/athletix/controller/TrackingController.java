package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.enums.NotificationEnum;
import com.athletix.service.NotificationService;

@Controller
@RequestMapping("/tracking")
public class TrackingController {
    private static final Logger log = LoggerFactory.getLogger(TrackingController.class);

    @Autowired
    private NotificationService notificationService;

    @GetMapping("")
    public String showMyTrackings() {
        log.info("Tracking page accessed");
        return "pages/tracking";
    }

    @GetMapping("/create")
    public String createTrackingForm() {
        log.info("Create tracking page accessed");
        return "pages/tracking/createTracking";
    }

    @PostMapping("/create")
    public String createTracking() {

        notificationService.createNotificationForUser(
                1,
                "New tracking created",
                "Your tracking has been created successfully",
                NotificationEnum.CREATE_TRACKING);

        return "redirect:/tracking";
    }

}
