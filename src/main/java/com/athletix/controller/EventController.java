package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    @GetMapping("")
    public String getEvents() {
        log.info("Accessing the event page");

        return "pages/event";
    }

    @GetMapping("/create")
    public String createEvent() {
        log.info("Accessing the event creation form");
        
        return "pages/event/eventCreationForm";
    }

}
