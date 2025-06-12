package com.athletix.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.athletix.enums.GenderEnum;
import com.athletix.enums.RoleEnum;
import com.athletix.model.Users;
import com.athletix.model.UsersTypes;

import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCardDTO {
    private String username;
    private String fullName;
    private String profileImage;
    private Users trainer;
    private String gmail;
    private String height;
    private String weight;
    private GenderEnum gender;
    private RoleEnum userType;

}
