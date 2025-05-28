package com.athletix.model;

import java.io.Serializable;

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
@Table(name = "images")
public class Images implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageURL;

    private Integer width;
    
    private Integer height;

    @ManyToOne
    @JoinColumn(name = "tracking_id")
    private Trackings tracking;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenges challenge;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Events event;
}
