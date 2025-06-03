package com.athletix.model.DTO;

import com.athletix.enums.EventRoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventParticipantsDTO {
    private String username;
    private String fullName;
    private String profileImage;
    private EventRoleEnum role;
}
