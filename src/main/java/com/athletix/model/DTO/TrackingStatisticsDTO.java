package com.athletix.model.DTO;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingStatisticsDTO {
    private int totalTrackings;
    private float totalDistance;
    private String totalDuration;
    private String favoriteSport;
    private Map<String, Float> monthlyKm;
}
