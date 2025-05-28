package com.athletix.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.athletix.model.Notifications;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/notification")
public class NotificationsController {

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteNotification(@RequestParam("id") Integer id, HttpSession session) {
        List<Notifications> notifications = (List<Notifications>) session.getAttribute("notifications");
        
        if (notifications != null) {
            notifications.removeIf(n -> id.equals(n.getId()));
            session.setAttribute("notifications", notifications);
        }

        return ResponseEntity.ok().build();
    }

}
