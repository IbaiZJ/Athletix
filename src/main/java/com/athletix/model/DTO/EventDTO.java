package com.athletix.model.DTO;

import java.time.LocalDateTime;

import com.athletix.enums.DifficultyEnum;
import com.athletix.enums.SportEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDTO {
    private Integer id;
    private String title;
    private String shortDescription;
    private String description;
    private LocalDateTime date;
    private Float km;
    private String location;
    private Double latitude;
    private Double longitude;
    private SportEnum activity;
    private DifficultyEnum difficulty;
    private String profileImage;
    private Integer participantsCount;

    public EventDTO(Integer id, String title, String shortDescription, String description,
            LocalDateTime date, Float km, String location, Double latitude, Double longitude,
            SportEnum activity, DifficultyEnum difficulty, String profileImage, Long participantsCount) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.date = date;
        this.km = km;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.activity = activity;
        this.difficulty = difficulty;
        this.profileImage = profileImage;
        this.participantsCount = participantsCount != null ? participantsCount.intValue() : 0;
    }
}
