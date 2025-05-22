package com.athletix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tracking")
public class TrackingController {

    @GetMapping("")
    public String showMyTracking() {
        return "pages/tracking";
    }

}
