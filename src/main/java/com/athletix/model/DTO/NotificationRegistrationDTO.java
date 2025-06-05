package com.athletix.model.DTO;

import java.time.LocalDate;

import com.athletix.enums.NotificationEnum;
import com.athletix.model.Notifications;
import com.athletix.model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRegistrationDTO {
    private String title;
    private String message;
    private String type;

    public NotificationRegistrationDTO(String title, String message, NotificationEnum notificationEnum) {
        this.title = title;
        this.message = message;
        this.type = notificationEnum != null ? notificationEnum.name() : null;
    }

    public Notifications toEntity(Users user) {
        Notifications notification = new Notifications();
        notification.setTitle(this.title);
        notification.setMessage(this.message);
        notification.setDate(LocalDate.now());
        notification.setType(this.type != null ? NotificationEnum.valueOf(this.type) : null);
        notification.setUser(user);
        return notification;
    }
}
