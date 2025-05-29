package com.athletix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.model.Events;
import com.athletix.model.DTO.EventRegistrationDTO;
import com.athletix.repository.EventRepository;

import jakarta.transaction.Transactional;

@Service
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        log.info("TrackingService initialized");
    }

    @Transactional
    public void createEvent(EventRegistrationDTO eventDTO) {
        Events event = eventDTO.toEntity();
    }
}
