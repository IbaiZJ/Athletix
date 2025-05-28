package com.athletix.util;

import java.time.Duration;
import java.util.List;

import com.athletix.model.DTO.TrackingCardDTO;
import com.athletix.model.Images;
import com.athletix.model.Trackings;
import com.athletix.model.Users;

public class TrackingUtil {

    public static TrackingCardDTO toTrackingCardDTO(Trackings tracking, List<Images> images) {
        Users user = tracking.getUser();

        return new TrackingCardDTO(
                user.getUsername(),
                user.getName() + " " + user.getSurname(),
                user.getProfileImage(),
                tracking.getTitle(),
                tracking.getDescription(),
                tracking.getKm(),
                formatDuration(tracking.getDuration()),
                // formatPace(tracking.getPace()),
                "5:23",
                tracking.getDate(),
                // images.stream().map(Images::getImageURL).toList()
                null);
    }

    private static String formatDuration(Duration duration) {
        if (duration == null)
            return "";
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
        if (pace == null)
            return "";
        long minutes = pace.toMinutes();
        long seconds = pace.minusMinutes(minutes).getSeconds();
        return String.format("%d:%02d /km", minutes, seconds);
    }
}
