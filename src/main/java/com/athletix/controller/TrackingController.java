package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tracking")
public class TrackingController {
    private static final Logger log = LoggerFactory.getLogger(TrackingController.class);

    @GetMapping("")
    public String showMyTrackings() {
        log.info("Tracking page accessed");
        return "pages/tracking";
    }

}
