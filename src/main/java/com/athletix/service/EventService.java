package com.athletix.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.model.DTO.EventCardDTO;
import com.athletix.model.DTO.EventRegistrationDTO;
import com.athletix.model.Events;
import com.athletix.model.Users;
import com.athletix.model.UsersEvents;
import com.athletix.repository.EventRepository;
import com.athletix.repository.UserEventRepository;

import jakarta.transaction.Transactional;

@Service
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final UserEventRepository userEventRepository;

    public EventService(
            EventRepository eventRepository,
            UserEventRepository userEventRepository) {
        this.eventRepository = eventRepository;
        this.userEventRepository = userEventRepository;
        log.info("TrackingService initialized");
    }

    @Transactional
    public void createEvent(Users user, EventRegistrationDTO eventDTO) {

        // Create event
        Events event = eventDTO.toEntity();
        eventRepository.save(event);

        // Create N-N 
        UsersEvents userEvent = new UsersEvents();
        userEvent.setRole(null);
        userEvent.setRegistrationDate(LocalDateTime.now());
        userEvent.setUser(user);
        userEvent.setEvent(event);
        userEventRepository.save(userEvent);

        log.info("Created new event with title: {} for user: {}", event.getTitle(), user.getUsername());
    }

    public List<EventCardDTO> getRegisteredEvents(Users user) {
        log.info("Fetching registered events for user: {}", user.getUsername());
        return userEventRepository.findRegisteredEventsByUser(user);
    }

    public List<EventCardDTO> getAvailableEvents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableEvents'");
    }

    public List<EventCardDTO> getMyEvents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMyEvents'");
    }

    public List<EventCardDTO> getFriendsEvents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFriendsEvents'");
    }
}
