package com.athletix.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.model.Friends;
import com.athletix.model.Users;
import com.athletix.model.DTO.ChatMessage;
import com.athletix.model.Messages;
import com.athletix.repository.MessageRepository;

@Service
public class ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    private final MessageRepository messageRepository;
    private final FriendService friendService;
    private final UserService userService;

    public ChatService(
            MessageRepository messageRepository,
            FriendService friendService,
            UserService userService) {
        this.messageRepository = messageRepository;
        this.friendService = friendService;
        this.userService = userService;
    }

    public void sendMessage(ChatMessage chatMessage) {
        Integer friendshipId = friendService.getFriendsId(
                chatMessage.getSender(),
                chatMessage.getRecipient());

        Friends friendship = friendService.findById(friendshipId)
                .orElseThrow(() -> new IllegalStateException("Friendship not found"));

        Users sender = userService.findByUsername(chatMessage.getSender());

        Messages message = new Messages();
        message.setMessage(chatMessage.getContent());
        message.setDate(LocalDateTime.now());
        message.setReaded(false);
        message.setFriendship(friendship);
        message.setSender(sender);

        messageRepository.save(message);

        log.info("Message saved from {} to {}", chatMessage.getSender(), chatMessage.getRecipient());
    }

    public List<Messages> getMessagesBetweenUsers(String username1, String username2) {
        Users user1 = userService.findByUsername(username1);
        Users user2 = userService.findByUsername(username2);

        Integer friendship = friendService.getFriendsId(user1.getUsername(), user2.getUsername());
        if (friendship == null) {
            throw new IllegalStateException("Friendship does not exist between users");
        }

        Friends friendshipEntity = friendService.findById(friendship)
                .orElseThrow(() -> new IllegalStateException("Friendship entity not found"));

        return messageRepository.findByFriendshipOrderByDateAsc(friendshipEntity);
    }

}
