package com.athletix.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.athletix.enums.GenderEnum;
import com.athletix.enums.RoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private String surname2;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private String town;

    private String height;

    private String weight;

    private String phone;

    private String birthDate;

    private String profileImage;

    @ManyToOne
    private Users trainer;

    @OneToMany(mappedBy = "trainer")
    private Set<Users> trainees;

    @Enumerated(EnumType.STRING)
    private RoleEnum userType;

    @OneToMany(mappedBy = "user")
    private Set<UsersEvents> events = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Notifications> notifications = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Trackings> trackings = new HashSet<>();

}
