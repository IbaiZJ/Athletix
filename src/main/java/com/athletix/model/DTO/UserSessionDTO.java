package com.athletix.model.DTO;

import com.athletix.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSessionDTO {
    private String username;
    private String name;
    private String surname;
    private String surname2;
    private String profileImage;

    public UserSessionDTO(User user) {
        username = user.getUsername();
        name = user.getName();
        surname = user.getSurname();
        surname2 = user.getSurname2();
        profileImage = user.getProfileImage();
    }

}
