package com.athletix.model.DTO;

import java.time.Duration;
import java.time.LocalDateTime;

import com.athletix.model.Trackings;
import com.athletix.model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingRegistrationDTO {
    private String title;
    private String description;
    private Float km;
    private Integer durationH;
    private Integer durationM;
    private Integer durationS;

    public Trackings toEntity(Users user) {
        Trackings tracking = new Trackings();

        Duration duration = Duration.ofHours(
            this.durationH != null ? this.durationH : 0
        ).plusMinutes(
            this.durationM != null ? this.durationM : 0
        ).plusSeconds(
            this.durationS != null ? this.durationS : 0
        );

        tracking.setTitle(this.title);
        tracking.setDescription(this.description);
        tracking.setKm(this.km);
        tracking.setPace(null);
        tracking.setDuration(duration);
        tracking.setDate(LocalDateTime.now());
        tracking.setUser(user);
        return tracking;
    }
}
