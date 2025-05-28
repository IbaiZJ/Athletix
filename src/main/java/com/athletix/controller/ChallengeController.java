package com.athletix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/challenge")
public class ChallengeController {
    private static final Logger log = LoggerFactory.getLogger(ChallengeController.class);

    @GetMapping("")
    public String getChallenges() {
        return "pages/challenge";
    }
    

}
