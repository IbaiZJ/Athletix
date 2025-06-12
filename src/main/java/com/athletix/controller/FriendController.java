package com.athletix.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.service.FriendService;

@Controller
@RequestMapping("/friend")
public class FriendController {
    private static final Logger log = LoggerFactory.getLogger(FriendController.class);

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
        log.info("FriendController initialized");
    }

    @GetMapping("")
    public String getFriends(Model model, Principal principal) {
        model.addAttribute("friends", friendService.getFriendsDTOs(principal));

        log.info("Accessing the friends page");
        return "pages/friend";
    }
}
