package com.athletix.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.enums.NotificationEnum;
import com.athletix.model.DTO.EventDTO;
import com.athletix.model.DTO.EventParticipantsDTO;
import com.athletix.model.DTO.EventRegistrationDTO;
import com.athletix.model.DTO.NotificationRegistrationDTO;
import com.athletix.model.Events;
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
        // Get current user
        Users user = userService.getCurrentUser();

        // Registered events
        List<EventDTO> registeredEvents = eventService.getRegisteredEvents(user);
        model.addAttribute("registeredEvents", registeredEvents);
        System.out.println(registeredEvents);
        log.info("Save registered events in model");

        // Available events
        List<EventDTO> availableEvents = eventService.getAvailableEvents(user);
        model.addAttribute("availableEvents", availableEvents);
        log.info("Save available events in model");

        // My events
        List<EventDTO> myEvents = eventService.getMyEvents(user);
        model.addAttribute("myEvents", myEvents);
        log.info("Save my events in model");
        /*
         * // Friends events
         * List<EventDTO> friendsEvents = eventService.getFriendsEvents();
         * model.addAttribute("friendsEvents", friendsEvents);
         * log.info("Save friends events in model");
         */

        log.info("Accessing the event page");

        return "pages/event";
    }

    @GetMapping("/{id}")
    public String getEventPage(@PathVariable("id") Integer id, Model model) {
        // Check if event exists
        Events event = eventService.getEventById(id);
        if (event == null) {
            log.error("Event not found: {}", id);
            return "error/404";
        }

        // Get event
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setShortDescription(event.getShortDescription());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setDate(event.getDate());
        eventDTO.setKm(event.getKm());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setDifficulty(event.getDifficulty());
        eventDTO.setParticipantsCount(eventService.getParticipantsCount(id));

        model.addAttribute("eventPage", eventDTO);

        return "pages/event/eventPage";
    }

    @GetMapping("/{id}/participants")
    public String getEventParticipants(@PathVariable("id") Integer id, Model model) {
        List<EventParticipantsDTO> participants = eventService.getEventParticipants(id);
        model.addAttribute("participants", participants);
        log.info("Participants for event {}: {}", id, participants);
        
        return "pages/event/eventParticipants";
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

    @GetMapping("/{id}/edit")
    public String editEventForm(@PathVariable("id") Integer id) {
        return null;
    }

    @PutMapping("/{id}/edit")
    public String editEvent(@PathVariable("id") Integer id) {
        return null;
    }

    @DeleteMapping("/{id}/delete")
    public String deleteEvent(@PathVariable("id") Integer id) {
        return null;
    }
}
