package com.athletix.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.model.DTO.NotificationRequestDTO;
import com.athletix.model.Notifications;
import com.athletix.model.Users;
import com.athletix.repository.NotificationRepository;
import com.athletix.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(
            NotificationRepository notificationRepository,
            UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        log.info("NotificationService initialized");
    }

    public void reloadNotifications(HttpServletRequest request, Users user) {
        log.info("Reloading notifications from the database");
        HttpSession session = request.getSession(false);
        List<Notifications> notifications = this.getNotificationsByUser(user);
        if (session != null && notifications != null) {
            session.setAttribute("notifications", notifications);
        }
    }

    public List<Notifications> getNotificationsByUser(Users user) {
        log.info("Fetching notifications for user: {}", user.getUsername());
        return notificationRepository.findByUserOrderByDateDesc(user);
    }

    @Transactional
    public void createNotificationForUser(Users user, NotificationRequestDTO notificationDTO) {
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("Usuario no encontrado");
        }
        if (notificationDTO.getTitle() == null || notificationDTO.getTitle().isEmpty()
                || notificationDTO.getMessage() == null || notificationDTO.getMessage().isEmpty()) {
            throw new IllegalArgumentException("Título y mensaje son obligatorios");
        }
        if (notificationDTO.getType() == null) {
            throw new IllegalArgumentException("Tipo de notificación es obligatorio");
        }

        Notifications notification = notificationDTO.toEntity(user);
        notificationRepository.save(notification);

        log.info("Notification created for user: {} with title: {}", user.getUsername(), notification.getTitle());
    }
}
