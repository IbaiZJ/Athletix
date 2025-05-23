package com.athletix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notifications")
public class NotificationsController {

    // @Autowired
    // private NotificationService notificationService;

    // // @PostMapping("/create")
    // public ResponseEntity<UsersNotifications> createNotification(@RequestBody NotificationRequestDTO request) {

    //     try {
    //         NotificationEnum type = NotificationEnum.valueOf(request.getType().toUpperCase());
    //         UsersNotifications result = notificationService.createNotificationForUser(
    //                 request.getUserId(),
    //                 request.getTitle(),
    //                 request.getMessage(),
    //                 type);
    //         return ResponseEntity.ok(result);
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.badRequest().body(null);
    //     }

    // }

}
