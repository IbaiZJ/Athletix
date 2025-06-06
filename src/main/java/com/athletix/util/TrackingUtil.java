package com.athletix.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.context.i18n.LocaleContextHolder;

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

        stats.setTotalTrackings(trackings.size());
        stats.setTotalDistance(calculateTotalDistance(trackings));
        stats.setTotalDuration(formatDuration(calculateTotalDuration(trackings)));
        stats.setFavoriteSport(null);
        stats.setMonthlyKm(getMonthlyKmLastMonths(trackings, 4));

        return stats;
    }

    private static float calculateTotalDistance(List<Trackings> trackings) {
        float totalDistance = 0f;
        for (Trackings t : trackings) {
            if (t.getKm() != null)
                totalDistance += t.getKm();
        }
        return totalDistance;
    }

    private static Duration calculateTotalDuration(List<Trackings> trackings) {
        Duration totalDuration = Duration.ZERO;
        for (Trackings t : trackings) {
            if (t.getDuration() != null)
                totalDuration = totalDuration.plus(t.getDuration());
        }
        return totalDuration;
    }

    private static Map<String, Float> getMonthlyKmLastMonths(List<Trackings> trackings, int months) {
        LocalDate now = LocalDate.now();
        List<YearMonth> recentMonths = IntStream.rangeClosed(0, months - 1)
                .mapToObj(i -> YearMonth.from(now.minusMonths(months - 1 - i)))
                .collect(Collectors.toList());

        Map<YearMonth, Float> grouped = new LinkedHashMap<>();
        for (YearMonth ym : recentMonths) {
            grouped.put(ym, 0f);
        }

        for (Trackings t : trackings) {
            if (t.getDate() != null && t.getKm() != null) {
                YearMonth ym = YearMonth.from(t.getDate());
                if (grouped.containsKey(ym)) {
                    grouped.merge(ym, t.getKm(), Float::sum);
                }
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", LocaleContextHolder.getLocale());
        Map<String, Float> result = new LinkedHashMap<>();
        for (YearMonth ym : recentMonths) {
            result.put(ym.format(formatter), grouped.getOrDefault(ym, 0f));
        }

        return result;
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
