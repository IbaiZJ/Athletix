package com.athletix.model.DTO;

import java.time.Duration;
import java.time.LocalDateTime;

import com.athletix.model.Exercises;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRegistrationDTO {
    private String title;
    private String description;
    private Float km;
    private Integer durationH;
    private Integer durationM;
    private Integer durationS;

    // Eliminamos targetUserId porque la asignación va por separado

    public Exercises toEntity() {
        Exercises exercise = new Exercises();

        Duration duration = Duration.ofHours(this.durationH != null ? this.durationH : 0)
                .plusMinutes(this.durationM != null ? this.durationM : 0)
                .plusSeconds(this.durationS != null ? this.durationS : 0);

        exercise.setTitle(this.title);
        exercise.setDescription(this.description);
        exercise.setKm(this.km);
        exercise.setDuration(duration);
        exercise.setDate(LocalDateTime.now());

        // No se setea creador ni asignación aquí
        // Se hará en el servicio para mantener separación de responsabilidades

        return exercise;
    }
}
