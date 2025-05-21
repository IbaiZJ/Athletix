package com.athletix.model.DTO;

import org.springframework.web.multipart.MultipartFile;

import com.athletix.enums.GenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    private Integer id;
    private String username;
    private String password;
    private String repeatPassword;
    private String email;
    private String name;
    private String surname;
    private String surname2;
    private GenderEnum gender;
    private String town;
    private String height;
    private String weight;
    private String birthDate;
    private String phone;
    private String profileImageURL;
    private MultipartFile profileImage;
}
