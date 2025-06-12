package com.athletix.controller;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.athletix.model.DTO.ChatMessage;
import com.athletix.model.Users;
import com.athletix.service.ChatService;
import com.athletix.service.FriendService;
import com.athletix.service.UserService;

@Controller
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private final FriendService friendService;
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;

    public ChatController(FriendService friendService, ChatService chatService, SimpMessagingTemplate messagingTemplate,
            UserService userService) {
        this.friendService = friendService;
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
        this.userService = userService;
    }

    @GetMapping("/chat")
    public String getChats(Model model, Principal principal) {

        model.addAttribute("friends", friendService.getFriendChatDTOs(principal));

        log.info("Chats page accessed");
        return "pages/chat";
    }

    @GetMapping("/chat/{friend}")
    public String getChatBetweenUsers(@PathVariable("friend") String friend, Principal principal, Model model)
            throws AccessDeniedException {
        String currentUsername = principal.getName();

        Users f = userService.findByUsername(friend);
        if (f == null) {
            throw new AccessDeniedException("Friend not found");
        }

        if (!friendService.areFriends(currentUsername, friend)) {
            throw new IllegalStateException("You can only chat with friends");
        }

        model.addAttribute("friend", f);
        model.addAttribute("messages", chatService.getMessagesBetweenUsers(currentUsername, friend));

        log.info("Chatting with " + friend);

        return "pages/chat/friendChat";
    }

    @MessageMapping("/chat.sendMessage")
    // @SendToUser("/queue/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, Principal principal) {
        chatService.sendMessage(chatMessage);

        // Enviar al remitente
        messagingTemplate.convertAndSendToUser(chatMessage.getSender(), "/queue/messages", chatMessage);
        // Enviar al receptor
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipient(), "/queue/messages", chatMessage);

        log.info("Message sent from {} to {}", chatMessage.getSender(), chatMessage.getRecipient());

        return chatMessage;
    }
}
