package com.athletix.util;

import java.time.Duration;
import java.util.List;

import com.athletix.model.DTO.TrackingCardDTO;
import com.athletix.model.DTO.TrackingStatisticsDTO;
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
                formatPace(tracking.getPace()),
                tracking.getDate(),
                // images.stream().map(Images::getImageURL).toList()
                null);
    }

    public static TrackingStatisticsDTO getStatistics(List<Trackings> trackings) {
        TrackingStatisticsDTO stats = new TrackingStatisticsDTO();
        
        Float totalDistance = 0f;
        Float totalTime = 0f;
        for (Trackings tracking : trackings) {
            if (tracking.getKm() != null)
            totalDistance += tracking.getKm();
            // if (tracking.getTime() != null) totalTime += tracking.getTime();
        }

        stats.setTotalTrackings(trackings.size());
        stats.setTotalDistance(totalDistance);
        stats.setTotalDuration(null);
        stats.setFavoriteSport(null);

        return stats;
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
        return String.format("%d:%02d min/km", minutes, seconds);
    }
}
