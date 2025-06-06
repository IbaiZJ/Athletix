package com.athletix.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exercises")
public class Exercises implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private Float km;

    private Duration duration;

    private LocalDateTime date;

    // Usuario que crea el ejercicio
    @ManyToOne
    @JoinColumn(name = "user_id") // Cambiado por claridad
    private Users creator;

    // Lista de asignaciones a usuarios
    @OneToMany(mappedBy = "exercise")
    private List<Asignaciones> asignaciones;
}
