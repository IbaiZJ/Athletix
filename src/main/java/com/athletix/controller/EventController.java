package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.service.EventService;


@Controller
@RequestMapping("/event")
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public String getEvents() {
        log.info("Accessing the event page");

        return "pages/event";
    }

    @GetMapping("/create")
    public String createEventForm() {
        log.info("Accessing the event creation form");
        
        return "pages/event/eventCreationForm";
    }

    @PostMapping("/create")
    public String createEvent() {
        //TODO: process POST request
        
        return "redirect:/event";
    }
    

}
