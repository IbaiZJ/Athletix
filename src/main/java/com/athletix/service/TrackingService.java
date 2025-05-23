package com.athletix.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.model.Trackings;
import com.athletix.model.Users;
import com.athletix.repository.TrackingRepository;

import jakarta.transaction.Transactional;

@Service
public class TrackingService {
    private static final Logger log = LoggerFactory.getLogger(TrackingService.class);

    private final TrackingRepository trackingRepository;

    public TrackingService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    public List<Trackings> getTrackingsByUser(Users user) {
        log.info("Fetching trackings for user: {}", user.getUsername());
        return trackingRepository.findByUserOrderByDateDesc(user);
    }

    @Transactional
    public Trackings createTracking(Users user, Trackings tracking) {
        // 1. Validate tracking
        if (tracking.getTitle() == null || tracking.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Nombre de seguimiento es obligatorio");
        }

        // 2. Create the tracking
        Trackings newTracking = new Trackings();
        newTracking.setTitle(tracking.getTitle());
        newTracking.setDescription(tracking.getDescription());
        newTracking.setUser(user);
        // newTracking.setDate(LocalDate.now());
        newTracking.setKm(tracking.getKm());
        Trackings savedTracking = trackingRepository.save(newTracking);

        log.info("Created new tracking with Title: {} for user: {}", savedTracking.getTitle(), user.getUsername());

        return savedTracking;
    }
}
