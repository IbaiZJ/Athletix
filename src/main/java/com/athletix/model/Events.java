package com.athletix.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.athletix.enums.DifficultyEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Events implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String shortDescription;

    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    private Float km;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    private DifficultyEnum difficulty;

    @OneToMany(mappedBy = "event")
    private Set<UsersEvents> users = new HashSet<>();

}
