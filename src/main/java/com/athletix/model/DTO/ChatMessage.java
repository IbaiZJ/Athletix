package com.athletix.model.DTO;

import java.time.LocalDateTime;

import com.athletix.enums.MessageType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String sender;
    private String recipient;
    private String content;
    private LocalDateTime timestamp;
    private MessageType type;
}
