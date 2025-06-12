package com.athletix.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.athletix.enums.DifficultyEnum;
import com.athletix.enums.EventRoleEnum;
import com.athletix.enums.NotificationEnum;
import com.athletix.enums.SportEnum;
import com.athletix.model.DTO.EventDTO;
import com.athletix.model.DTO.EventParticipantsDTO;
import com.athletix.model.DTO.EventRegistrationDTO;
import com.athletix.model.DTO.NotificationRegistrationDTO;
import com.athletix.model.Events;
import com.athletix.model.Users;
import com.athletix.service.EventService;
import com.athletix.service.FileStorageService;
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
    private final FileStorageService fileStorageService;
    private final SimpMessagingTemplate messagingTemplate;

    public EventController(
            EventService eventService,
            UserService userService,
            NotificationService notificationService,
            FileStorageService fileStorageService,
            SimpMessagingTemplate messagingTemplate) {
        this.eventService = eventService;
        this.userService = userService;
        this.notificationService = notificationService;
        this.fileStorageService = fileStorageService;
        this.messagingTemplate = messagingTemplate;
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
        // List<EventDTO> myEvents = eventService.getMyEvents(user);
        // model.addAttribute("myEvents", myEvents);
        // log.info("Save my events in model");
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

        // Get current user
        Users user = userService.getCurrentUser();

        // Get event
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setShortDescription(event.getShortDescription());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setDate(event.getDate());
        eventDTO.setKm(event.getKm());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setLatitude(event.getLatitude());
        eventDTO.setLongitude(event.getLongitude());
        eventDTO.setActivity(event.getActivity());
        eventDTO.setDifficulty(event.getDifficulty());
        eventDTO.setProfileImage(event.getProfileImage());
        eventDTO.setParticipantsCount(eventService.getParticipantsCount(id));
        eventDTO.setUserRole(eventService.findUserRoleByEventId(id, user.getId()));
        eventDTO.setFollowing(eventService.existsByUserIdAndEventId(user.getId(), id));

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

    @PostMapping("/{id}/participants/{username}/delete")
    public String deleteEventParticipant(@PathVariable("id") Integer id, @PathVariable("username") String username, RedirectAttributes redirect) {
        log.info("Deleting participant {} from event {}", username, id);
        eventService.deleteEventParticipant(id, username);
        // redirect.addFlashAttribute("success", "Participant deleted successfully");
        return "redirect:/event/" + id + "/participants";
    }

    @GetMapping("/create")
    public String createEventForm(Model model) {
        EventRegistrationDTO dto = new EventRegistrationDTO();
        dto.setActivity(SportEnum.RUNNING);
        dto.setDifficulty(DifficultyEnum.EASY);

        model.addAttribute("eventEditForm", dto);
        log.info("Accessing the event creation form");

        return "pages/event/eventForm";
    }

    @PostMapping("/create")
    public String createEvent(EventRegistrationDTO eventDTO, HttpServletRequest request, RedirectAttributes redirect) {
        // Get current user
        Users user = userService.getCurrentUser();
        log.info("Creating new event for user: {}", user.getUsername());

        try {
            if (eventDTO.getProfileImage() != null && !eventDTO.getProfileImage().isEmpty()) {
                String fileName = fileStorageService.storeFile(eventDTO.getProfileImage());
                eventDTO.setProfileImageURL("/uploads/" + fileName);
            }

            // Create event
            eventService.createEvent(user, eventDTO);
            log.info("Event created for user: {}", user.getUsername());

            // Create notification
            NotificationRegistrationDTO notification = new NotificationRegistrationDTO("New event created",
                    "You have created a new event: " + eventDTO.getTitle(), NotificationEnum.CREATE_EVENT);
            notificationService.createNotificationForUser(user, notification);
            notificationService.reloadNotifications(request, user);

            log.info("Creating new tracking with title: {}", eventDTO.getTitle());

        } catch (IllegalArgumentException e) {
            // model.addAttribute("error", e.getMessage());
            log.error("Error creating account: {}", e.getMessage());
            // redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/event/create";
        } catch (IOException e) {
            log.error("Unexpected error during registration", e);
            // redirect.addFlashAttribute("error", "Ha ocurrido un error inesperado.");
            return "redirect:/event/create";
        }

        return "redirect:/event";
    }

    @GetMapping("/{id}/edit")
    public String editEventForm(@PathVariable("id") Integer id, Model model) {
        Events event = eventService.findById(id);
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado");
        }

        EventRegistrationDTO dto = fromEntity(event);
        model.addAttribute("eventEditForm", dto);
        System.out.println(dto);

        log.info("Accessing the event edit form");

        return "pages/event/eventForm";
    }

    public EventRegistrationDTO fromEntity(Events event) {
        EventRegistrationDTO dto = new EventRegistrationDTO();

        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setShortDescription(event.getShortDescription());
        dto.setDescription(event.getDescription());

        LocalDateTime dateTime = event.getDate();
        dto.setDate(dateTime.toLocalDate().toString());
        dto.setDateH(dateTime.getHour());
        dto.setDateM(dateTime.getMinute());

        dto.setKm(event.getKm());
        dto.setLocation(event.getLocation());
        dto.setLatitude(event.getLatitude());
        dto.setLongitude(event.getLongitude());
        dto.setActivity(event.getActivity());
        dto.setDifficulty(event.getDifficulty());
        dto.setProfileImageURL(event.getProfileImage());

        return dto;
    }

    @PostMapping("/{id}/edit")
    public String editEvent(@PathVariable("id") Integer id, EventRegistrationDTO eventDTO, HttpServletRequest request) {
        System.out.println(eventDTO);

        // // Get current user
        Users user = userService.getCurrentUser();
        log.info("Editing event for user: {}", user.getUsername());

        try {
            if (eventDTO.getProfileImage() != null && !eventDTO.getProfileImage().isEmpty()
                    ) { // && eventDTO.getProfileImageURL() == null
                String fileName = fileStorageService.storeFile(eventDTO.getProfileImage());
                eventDTO.setProfileImageURL("/uploads/" + fileName);
                // TODO: remove all images from previous event
            }

            // Edit event
            eventService.updateEvent(user, eventDTO);
            log.info("Event created for user: {}", user.getUsername());

            // Create notification TODO
            // NotificationRegistrationDTO notification = new
            // NotificationRegistrationDTO("Event edited",
            // "You have edited a event: " + eventDTO.getTitle(),
            // NotificationEnum.EDIT_EVENT);
            // notificationService.createNotificationForUser(user, notification);
            // notificationService.reloadNotifications(request, user);

            log.info("Edited event with title: {}", eventDTO.getTitle());

        } catch (IllegalArgumentException e) {
            // model.addAttribute("error", e.getMessage());
            log.error("Error creating account: {}", e.getMessage());
            // redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/event/{id}/edit";
        } catch (IOException e) {
            log.error("Unexpected error during registration", e);
            // redirect.addFlashAttribute("error", "Ha ocurrido un error inesperado.");
            return "redirect:/event/{id}/edit";
        }

        return "redirect:/event/{id}";
    }

    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable("id") Integer id) {
        eventService.deleteEvent(id);
        log.info("Deleted event with ID: {}", id);

        return "redirect:/event";
    }

    // WEB SOCKETS
    @MessageMapping("/follow")
    public void followEvent(Integer eventId, Principal principal) {
        String username = principal.getName();
        Users user = userService.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        eventService.addUserToEvent(user, eventId, EventRoleEnum.PARTICIPANT);
        messagingTemplate.convertAndSendToUser(username, "/queue/event/follow", eventId);
    }

    @MessageMapping("/unfollow")
    public void unfollowEvent(Integer eventId, Principal principal) {
        String username = principal.getName();
        Users user = userService.findByUsername(username);

        if (user != null) {
            eventService.removeUserFromEvent(user, eventId);
            messagingTemplate.convertAndSendToUser(username, "/queue/event/unfollow", eventId.toString());
        }
    }
}
