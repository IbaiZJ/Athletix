package com.athletix.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.athletix.service.NotificationService;

@Controller
public class NotificationsController {
    private static final Logger log = LoggerFactory.getLogger(NotificationsController.class);

    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationsController(
            NotificationService notificationService,
            SimpMessagingTemplate messagingTemplate) {
        this.notificationService = notificationService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/notification/delete")
    public void deleteNotification(Integer notificationId, Principal principal) {
        notificationService.deleteNotification(notificationId);
        messagingTemplate.convertAndSendToUser(principal.getName() ,"/queue/notification/deleted", notificationId);       
    }

}
