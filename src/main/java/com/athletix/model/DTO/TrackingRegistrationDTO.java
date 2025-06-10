package com.athletix.model.DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import com.athletix.enums.DifficultyEnum;
import com.athletix.enums.SportEnum;
import com.athletix.model.Trackings;
import com.athletix.model.Users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrackingRegistrationDTO {
    private Integer id;
    private String title;
    private String description;
    private Float km;
    private Integer durationH;
    private Integer durationM;
    private Integer durationS;
    private SportEnum activity;
    private DifficultyEnum difficulty;

    public Trackings toEntity(Users user) {
        Trackings tracking = new Trackings();

        Duration duration = Duration.ofHours(
            Objects.requireNonNullElse(this.durationH, 0)
        ).plusMinutes(
            Objects.requireNonNullElse(this.durationM, 0)
        ).plusSeconds(
            Objects.requireNonNullElse(this.durationS, 0)
        );

        Duration pace = duration.dividedBy(km.longValue());
        
        tracking.setId(this.id);
        tracking.setTitle(this.title);
        tracking.setDescription(this.description);
        tracking.setKm(this.km);
        if (km != null && km > 0 && !duration.isZero()) {
            tracking.setPace(pace);
        } else {
            tracking.setPace(Duration.ZERO);
        }
        tracking.setDuration(duration);
        tracking.setDate(LocalDateTime.now());
        tracking.setUser(user);
        return tracking;
    }
}
