package com.athletix.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequestDTO {
    private Integer userId;
    private String title;
    private String message;
    private String type;
}
