package com.athletix.model.DTO;

import java.io.Serializable;

import com.athletix.model.Users;
import com.athletix.model.UsersTypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSessionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String name;
    private String surname;
    private String surname2;
    private String email;
    private String profileImage;
    private UsersTypes userType;
    private Users trainer;

    public UserSessionDTO(Users user) {
        id = user.getId();
        username = user.getUsername();
        name = user.getName();
        surname = user.getSurname();
        surname2 = user.getSurname2();
        email = user.getEmail();
        profileImage = user.getProfileImage();
        userType=user.getUserType();
        trainer=user.getTrainer();
    }
    
}
