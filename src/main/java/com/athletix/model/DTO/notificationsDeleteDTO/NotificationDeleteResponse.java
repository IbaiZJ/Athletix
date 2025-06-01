package com.athletix.model.DTO.notificationsDeleteDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationDeleteResponse {
    private Integer notificationId;
    private boolean success;
}
