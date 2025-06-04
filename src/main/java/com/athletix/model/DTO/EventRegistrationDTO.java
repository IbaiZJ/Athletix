package com.athletix.model.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import com.athletix.enums.DifficultyEnum;
import com.athletix.enums.SportEnum;
import com.athletix.model.Events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRegistrationDTO {
    private String title;
    private String shortDescription;
    private String description;
    private String date;
    private Integer dateH;
    private Integer dateM;
    private Float km;
    private String location;
    private Double latitude;
    private Double longitude;
    private SportEnum activity;
    private DifficultyEnum difficulty;
    private String profileImageURL;
    private MultipartFile profileImage;

    public Events toEntity() {
        Events event = new Events();

        int hour = Objects.requireNonNullElse(dateH, 0);
        int minute = Objects.requireNonNullElse(dateM, 0);

        LocalDateTime dateTime = LocalDateTime.of(
                LocalDate.parse(date),
                LocalTime.of(hour, minute));

        event.setTitle(this.title);
        event.setShortDescription(this.shortDescription);
        event.setDescription(this.description);
        event.setDate(dateTime);
        event.setKm(this.km);
        event.setLocation(this.location);
        event.setLatitude(this.latitude);
        event.setLongitude(this.longitude);
        event.setActivity(this.activity);
        event.setDifficulty(this.difficulty);
        event.setProfileImage(this.profileImageURL);

        return event;
    }

}
