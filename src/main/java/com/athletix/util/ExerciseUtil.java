package com.athletix.util;

import java.time.Duration;
import java.util.List;

import com.athletix.model.DTO.ExerciseCardDTO;
import com.athletix.model.Exercises;
import com.athletix.model.Images;
import com.athletix.model.Users;

public class ExerciseUtil {

    public static ExerciseCardDTO toExerciseCardDTO(Exercises exercise, List<Images> images) {
        Users user = exercise.getCreator(); // o getUser_id() si no has renombrado aún

        return new ExerciseCardDTO(
                user.getUsername(),
                user.getName() + " " + user.getSurname(),
                user.getProfileImage(),
                exercise.getTitle(),
                exercise.getDescription(),
                exercise.getKm(),
                formatDuration(exercise.getDuration()),
                // formatPace(pace) si lo tuvieras
                "5:23",
                exercise.getDate(),
                // images.stream().map(Images::getImageURL).toList()
                null
        );
    }

    private static String formatDuration(Duration duration) {
        if (duration == null) return "";
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        if (hours > 0) {
            return String.format("%dh %02dmin", hours, minutes);
        } else {
            return String.format("%dmin %02ds", minutes, seconds);
        }
    }

    private static String formatPace(Duration pace) {
        if (pace == null) return "";
        long minutes = pace.toMinutes();
        long seconds = pace.minusMinutes(minutes).getSeconds();
        return String.format("%d:%02d /km", minutes, seconds);
    }
}
