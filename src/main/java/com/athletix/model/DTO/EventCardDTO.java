package com.athletix.model.DTO;

import java.time.LocalDateTime;

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
public class EventCardDTO {
    private Integer id;
    private String title;
    private String shortDescription;
    private LocalDateTime date;
    private String location;
}
