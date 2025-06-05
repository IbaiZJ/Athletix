package com.athletix.model.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingCardDTO {
    // User
    private String username;
    private String fullName;
    private String profileImage;

    // Tracking
    private String title;
    private String description;
    private Float km;
    private String duration; 
    private String pace; 
    private LocalDateTime date;

    // Images
    private List<String> imageUrls;
}
