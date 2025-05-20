package com.athletix.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Usuario")
    private String username;

    @Column(name = "Contrasena")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "Nombre")
    private String name;

    @Column(name = "Apellido")
    private String surname;

    @Column(name = "Apellido2")
    private String surname2;

    @Column(name = "Genero")
    private String gender;

    @Column(name = "Pueblo")
    private String town;

    @Column(name = "Altura")
    private String height;

    @Column(name = "Peso")
    private String weight;

    @Column(name = "Telefono")
    private String phone;

    @Column(name = "Imagen")
    private String profileImage;

    @ManyToOne
    @JoinColumn(name = "IDentrenador")
    private User trainer;

    @ManyToOne
    @JoinColumn(name = "IDtipousuario")
    private UserType userType;

    @Column(name = "Rol")
    private String role;
}
