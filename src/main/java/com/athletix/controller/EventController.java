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
import com.athletix.model.DTO.EventCardDTO;
import com.athletix.model.DTO.EventRegistrationDTO;
import com.athletix.model.DTO.NotificationRegistrationDTO;
import com.athletix.model.Users;
import com.athletix.service.EventService;
import com.athletix.service.NotificationService;
import com.athletix.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/event")
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;
    private final UserService userService;
    private final NotificationService notificationService;

    public EventController(
            EventService eventService,
            UserService userService,
            NotificationService notificationService) {
        this.eventService = eventService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @GetMapping("")
    public String getEvents(Model model) {
        // Registered events
        List<EventCardDTO> registeredEvents = eventService.getRegisteredEvents(userService.getCurrentUser());
        model.addAttribute("registeredEvents", registeredEvents);
        log.info("Save registered events in model");
        /*
        // Available events
        List<EventCardDTO> availableEvents = eventService.getAvailableEvents();
        model.addAttribute("availableEvents", availableEvents);
        log.info("Save available events in model");
         
        // My events
        List<EventCardDTO> myEvents = eventService.getMyEvents();
        model.addAttribute("myEvents", myEvents);
        log.info("Save my events in model");

        // Friends events
        List<EventCardDTO> friendsEvents = eventService.getFriendsEvents();
        model.addAttribute("friendsEvents", friendsEvents);
        log.info("Save friends events in model");
        */

        log.info("Accessing the event page");

        return "pages/event";
    }

    @GetMapping("/create")
    public String createEventForm() {
        log.info("Accessing the event creation form");

        return "pages/event/eventCreationForm";
    }

    @PostMapping("/create")
    public String createEvent(EventRegistrationDTO eventDTO, HttpServletRequest request) {
        // Get current user
        Users user = userService.getCurrentUser();
        log.info("Creating new event for user: {}", user.getUsername());

        // Create event
        eventService.createEvent(user, eventDTO);
        log.info("Event created for user: {}", user.getUsername());

        // Create notification
        NotificationRegistrationDTO notification = new NotificationRegistrationDTO("New event created",
                "You have created a new event: " + eventDTO.getTitle(), NotificationEnum.CREATE_EVENT);
        notificationService.createNotificationForUser(user, notification);
        notificationService.reloadNotifications(request, user);

        log.info("Creating new tracking with title: {}", eventDTO.getTitle());

        return "redirect:/event";
    }

}
