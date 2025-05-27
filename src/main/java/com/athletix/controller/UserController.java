package com.athletix.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.athletix.model.DTO.UserRegistrationDTO;
import com.athletix.model.Users;
import com.athletix.service.FileStorageService;
import com.athletix.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final FileStorageService fileStorageService;

    public UserController(
            UserService userService,
            FileStorageService fileStorageService) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;
        log.info("UserController initialized");
    }

    @GetMapping("")
    public String getUserProfiles() {
        Users user = userService.getCurrentUser();
        log.info("Showing user profile for: {}", user.getUsername());

        return "redirect:/user/" + user.getUsername();
    }

    @GetMapping("/{username}")
    public String getUserProfileByUsername(@PathVariable("username") String username, Model model) {
        Users user = userService.findByUsername(username);
        if (user == null) {
            log.error("User not found: {}", username);
            return "error/404";
        }

        return "pages/profile/userProfile";
    }

    @GetMapping("/create")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
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

            redirect.addFlashAttribute("success", "Cuenta creada correctamente.");
            log.info("Account registered for user: {}", user.getUsername());

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
        } catch (Exception e) {
            log.error("Unexpected error during registration", e);
            redirect.addFlashAttribute("error", "Ha ocurrido un error inesperado.");
            return "redirect:/user/create";
        }
    }
}
