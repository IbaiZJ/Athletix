package com.athletix.model.DTO;

import com.athletix.model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSessionDTO {
    private String username;
    private String name;
    private String surname;
    private String surname2;
    private String email;
    private String profileImage;

    public UserSessionDTO(Users user) {
        username = user.getUsername();
        name = user.getName();
        surname = user.getSurname();
        surname2 = user.getSurname2();
        email = user.getEmail();
        profileImage = user.getProfileImage();
    }

}
