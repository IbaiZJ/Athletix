package com.athletix.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.athletix.model.DTO.ChatMessage;

@Controller
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    @GetMapping("/chat")
    public String getChats() {
        log.info("Chats page accessed");
        return "pages/chat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendToUser("/queue/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage,
            Principal principal) {
        chatMessage.setSender(principal.getName());
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendToUser("/queue/messages")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
            Principal principal) {
        chatMessage.setSender(principal.getName());
        chatMessage.setContent("Se ha conectado: " + principal.getName());
        return chatMessage;
    }
}
