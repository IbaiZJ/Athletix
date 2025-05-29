package com.athletix.model.DTO;

import java.time.LocalDateTime;

import com.athletix.model.Events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRegistrationDTO {
    private String title;
    private String description;
    private LocalDateTime date;
    private Integer km;
    private String location;

    public Events toEntity() {
        Events event = new Events();

        

        return event;
    }
}
