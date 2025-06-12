package com.athletix.model.DTO;

import java.io.Serializable;

import com.athletix.enums.GenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements  Serializable {
private static final long serialVersionUID = 1L;

 
    private String username;
    private String name;
    private String surname;
    private String surname2;
    private GenderEnum gender;
    private String town;
    private String height;
    private String weight;
    private String birthDate;
    private String profileImageURL;
}
