package com.athletix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athletix.enums.NotificationEnum;
import com.athletix.model.Notifications;
import com.athletix.model.Users;
import com.athletix.model.UsersNotifications;
import com.athletix.repository.NotificationRepository;
import com.athletix.repository.UserRepository;
import com.athletix.repository.UsersNotificationRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;
    private final UsersNotificationRepository userNotificationRepository;

    @Autowired
    private UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository,
            UsersNotificationRepository userNotificationRepository) {
        this.notificationRepository = notificationRepository;
        this.userNotificationRepository = userNotificationRepository;
    }

    // public List<UsersNotifications> getNotificationsByUser(Users user) {
    //     log.info("Fetching notifications for user: {}", user.getUsername());
    //     return userNotificationRepository.findByUserOrderByDateDesc(user);
    // }

    @Transactional
    public UsersNotifications createNotificationForUser(Integer userId, String title, String message,
            NotificationEnum notificationType) {
        // 1. Validate user and message
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        if (title == null || title.isEmpty() || message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Título y mensaje son obligatorios");
        }
        if (notificationType == null) {
            throw new IllegalArgumentException("Tipo de notificación es obligatorio");
        }

        // 2. Create the notification
        Notifications notification = new Notifications();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(notificationType);

        Notifications savedNotification = notificationRepository.save(notification);

        // 3. Assign the notification to the user
        Users user = new Users();
        user.setId(userId);

        UsersNotifications userNotification = new UsersNotifications();
        userNotification.setUser(user);
        userNotification.setNotification(savedNotification);

        log.info("Notification created for user: " + userId);

        return userNotificationRepository.save(userNotification);
    }
}
