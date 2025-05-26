package com.athletix.model.DTO;

import java.time.LocalDate;

import com.athletix.model.Trackings;
import com.athletix.model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingRegistrationDTO {
    private Integer id;
    private String title;
    private String description;
    private Float km;

    public Trackings toEntity(Users user) {
        Trackings tracking = new Trackings();
        tracking.setTitle(this.title);
        tracking.setDescription(this.description);
        tracking.setKm(this.km);
        tracking.setDate(LocalDate.now());
        tracking.setUser(user);
        return tracking;
    }
}
