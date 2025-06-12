package com.athletix.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.model.DTO.TrackingRegistrationDTO;
import com.athletix.model.Trackings;
import com.athletix.model.Users;
import com.athletix.repository.TrackingRepository;

import jakarta.transaction.Transactional;

@Service
public class TrackingService {
    private static final Logger log = LoggerFactory.getLogger(TrackingService.class);

    private final TrackingRepository trackingRepository;

    public TrackingService(
            TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
        log.info("TrackingService initialized");
    }

    @Transactional
    public List<Trackings> getTrackingsByUser(Users user) {
        log.info("Fetching trackings for user: {}", user.getUsername());
        return trackingRepository.findByUserOrderByDateDesc(user);
    }

    @Transactional
    public void createTracking(Users user, TrackingRegistrationDTO trackingDTO) {
        if (trackingDTO.getTitle() == null || trackingDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Tracking title cannot be null or empty");
        }

        Trackings tracking = trackingDTO.toEntity(user);
        trackingRepository.save(tracking);
        log.info("Created new tracking with Title: {} for user: {}", tracking.getTitle(), user.getUsername());
    }
}
