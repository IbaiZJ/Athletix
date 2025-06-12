package com.athletix.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.model.DTO.FriendRequestDTO;
import com.athletix.model.Users;
import com.athletix.service.FriendService;
import com.athletix.service.UserService;

@Controller
@RequestMapping("/friend")
public class FriendController {
    private static final Logger log = LoggerFactory.getLogger(FriendController.class);

    private final FriendService friendService;
    private final UserService userService;

    public FriendController(
            FriendService friendService,
            UserService userService) {
        this.friendService = friendService;
        this.userService = userService;
        log.info("FriendController initialized");
    }

    @GetMapping("")
    public String getFriends(Model model, Principal principal) {
        model.addAttribute("friends", friendService.getFriendsDTOs(principal));
        model.addAttribute("meetNewFriends", friendService.getNonFriendUsersDTO(principal));

        log.info("Accessing the friends page");
        return "pages/friend";
    }

    // WEB SOCKETS
    @MessageMapping("/add")
    public void addFriend(@Payload FriendRequestDTO request, Principal principal) {
        String myUsername = principal.getName();
        Users user = userService.findByUsername(myUsername);
        Users friend = userService.findByUsername(request.getUsername());

        if (user == null || friend == null) {
            throw new IllegalArgumentException("Usuario o amigo no encontrado");
        }

        friendService.createFriends(user, friend);
        log.info("Friend added: {}", request.getUsername());
    }

    @MessageMapping("/remove")
    public void removeFriend(@Payload FriendRequestDTO request, Principal principal) {
        String myUsername = principal.getName();
        Users user = userService.findByUsername(myUsername);
        Users friend = userService.findByUsername(request.getUsername());

        if (user != null && friend != null) {
            friendService.removeFriendship(user, friend);
        }

        
        log.info("Friend removed: {}", request.getUsername());
    }
}
