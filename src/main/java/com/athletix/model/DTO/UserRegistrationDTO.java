package com.athletix.model.DTO;

import java.io.Serializable;

import org.hibernate.usertype.UserType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.athletix.enums.GenderEnum;
import com.athletix.model.Users;
import com.athletix.model.UsersTypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    private String repeatPassword;
    // @Email
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
    private Users trainer;
    private UsersTypes type;

    public Users toEntity(PasswordEncoder encoder) {
        Users user = new Users();
        user.setUsername(this.username);
        user.setPassword(encoder.encode(this.password));
        user.setEmail(this.email);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setSurname2(this.surname2);
        user.setGender(this.gender);
        user.setTown(this.town);
        user.setHeight(this.height);
        user.setWeight(this.weight);
        user.setPhone(this.phone);
        user.setBirthDate(this.birthDate);
        user.setProfileImage(this.profileImageURL);
        user.setTrainer(this.trainer);
        user.setUserType(type);
        return user;
    }
}
