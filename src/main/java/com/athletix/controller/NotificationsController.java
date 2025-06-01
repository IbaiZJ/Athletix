package com.athletix.controller;

import org.springframework.stereotype.Controller;

import com.athletix.service.NotificationService;


@Controller
public class NotificationsController {

    private final NotificationService notificationService;

    public NotificationsController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // @MessageMapping("/notification/delete")
    // @SendTo("/topic/notification")
    // public NotificationDeleteResponse deleteNotification(NotificationDeleteRequest request, Principal principal) {
    //     // Remove notifications from backend
    //     notificationService.deleteNotification(request.getNotificationId(), principal.getName());

    //     // Return response to frontend
    //     return new NotificationDeleteResponse(request.getNotificationId(), true);
    // }

}
