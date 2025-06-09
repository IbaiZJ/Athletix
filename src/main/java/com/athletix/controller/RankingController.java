package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.service.UserService;


@Controller
@RequestMapping("/ranking")
public class RankingController {
    private static final Logger log = LoggerFactory.getLogger(RankingController.class);

    private final UserService userService;

    public RankingController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showRanking(Model model) {
        model.addAttribute("rankingUsers", userService.getUsersRankingByDistance());
        System.out.println(userService.getUsersRankingByDistance());

        log.info("Accessing the ranking page");
        return "pages/ranking";
    }

}
