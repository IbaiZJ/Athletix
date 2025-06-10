package com.athletix.model;

import java.io.Serializable;

import jakarta.persistence.*;

import com.athletix.util.AsignacionId;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "asignaciones")
@IdClass(AsignacionId.class) // ID compuesta
public class Asignaciones implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Id
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercises exercise;
}

