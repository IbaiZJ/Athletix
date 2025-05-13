package com.athletix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    @GetMapping("/services")
    public String login() {
        return "pages/services";
    }

}
