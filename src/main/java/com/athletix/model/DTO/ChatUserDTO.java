package com.athletix.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatUserDTO {
    private String username;
    private String name;
    private String surname;
    private String profileImage;
    private ChatMessage lastMessage;
}
