package com.athletix.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.athletix.model.DTO.UserRegistrationDTO;
import com.athletix.service.FileStorageService;
import com.athletix.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/create")
    public String showCreateAccountForm() {
        log.info("Create Account page accessed");
        return "pages/createAccount";
    }

    @PostMapping("/create")
    public String createAccount(@ModelAttribute("user") UserRegistrationDTO user, RedirectAttributes redirect) {
        try {
            if (user.getProfileImage() != null && !user.getProfileImage().isEmpty()) {
                String fileName = fileStorageService.storeFile(user.getProfileImage());
                user.setProfileImageURL("/uploads/" + fileName);
            }

            userService.registerUser(user);
            log.info("Account registed for user: {}", user.getUsername());
            return "redirect:/login?registered";
        } catch (IOException e) {
            log.error("Error al guardar la imagen: {}", e.getMessage());
            redirect.addFlashAttribute("error", "Error al guardar la imagen");
            return "redirect:/user/create";
        } catch (IllegalArgumentException e) {
            // model.addAttribute("error", e.getMessage());
            log.error("Error creating account: {}", e.getMessage());
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/user/create";
        }
    }
}
