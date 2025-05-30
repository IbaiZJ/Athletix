package com.athletix.model.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

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

        return event;
    }

}
