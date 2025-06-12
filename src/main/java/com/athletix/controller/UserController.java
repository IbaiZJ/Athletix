package com.athletix.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.athletix.model.DTO.UserDTO;

import com.athletix.model.DTO.UserRegistrationDTO;
import com.athletix.model.DTO.UserCardDTO;
import com.athletix.enums.NotificationEnum;
import com.athletix.enums.RoleEnum;
import com.athletix.model.Users;
import com.athletix.service.FileStorageService;
import com.athletix.service.NotificationService;
import com.athletix.service.UserService;
import com.athletix.util.UserValidationUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final NotificationService notificationService;
    private final FileStorageService fileStorageService;
    private final SimpMessagingTemplate messagingTemplate;
    

    public UserController(
            UserService userService,
            NotificationService notificationService,
            FileStorageService fileStorageService,
            SimpMessagingTemplate messagingTemplate) {
        this.userService=userService;
        this.notificationService = notificationService;
        this.fileStorageService = fileStorageService;
        this.messagingTemplate = messagingTemplate;
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

        UserDTO userdto = new UserDTO();
        userdto.setName(user.getName());
        userdto.setSurname(user.getSurname());
        userdto.setSurname2(user.getSurname2());
        userdto.setGender(user.getGender());
        userdto.setTown(user.getTown());
        userdto.setHeight(user.getHeight());
        userdto.setWeight(user.getWeight());
        userdto.setBirthDate(user.getBirthDate());
        userdto.setProfileImageURL(user.getProfileImage());

        model.addAttribute("userProfile", userdto);

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
    @GetMapping("/profiles")
        public String listAllUserProfiles(Model model) {
            Users sesuser=userService.getCurrentUser();
            String username=sesuser.getUsername();
            List<Users> users =userService.findAllUsers();
            List<UserCardDTO> userDTOs = new ArrayList<>();
            for (Users user : users) {
                if(username.equals(user.getUsername())){}else{
                userDTOs.add(UserValidationUtil.toUserCardDTO(user,null));}
            }
            model.addAttribute("users", userDTOs);
            log.info("Listing all user profiles: total {}", users.size());
            return "pages/profiles"; // Asegúrate de crear esta plantilla
        }

    @GetMapping("/profiles/{username}/assigned")
    public String chargeUserTrainer(@PathVariable("username") String username,Model model) {
        log.info("Create exercise page accessed");
        Users user = userService.findByUsername(username); // o lo que uses para obtenerlo
        model.addAttribute("user", user);
        return "pages/trainers/trainerForm";
    }
    @PostMapping("/profiles/{username}/assigned")
    public String assignTrainer(@PathVariable("username") String username, @RequestParam("trainer") String trainerUsername) {

        Users user = userService.findByUsername(username);

        if ("null".equals(trainerUsername)) {
            user.setTrainer(null);
        } else {
            Users trainer = userService.findByUsername(trainerUsername);
            user.setTrainer(trainer);
        }

        userService.save(user);

        return "redirect:/user/profiles";
    }      
     @GetMapping("/profiles/{username}/userType")
    public String chargeUserType(@PathVariable("username") String username,Model model) {
        log.info("Create exercise page accessed");
        Users user = userService.findByUsername(username); // o lo que uses para obtenerlo
        model.addAttribute("user", user);
        return "pages/admins/adminForm";
    }
    @PostMapping("/profiles/{username}/userType")
public String assignUserType(@PathVariable("username") String username, @RequestParam("userType") String userTypeStr) {
    Users user = userService.findByUsername(username);
    if (user == null) {
        throw new IllegalArgumentException("Usuario no encontrado: " + username);
    }

    RoleEnum newType;
    try {
        newType = RoleEnum.valueOf(userTypeStr.toUpperCase());
    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Tipo de usuario inválido: " + userTypeStr);
    }

    RoleEnum currentType = user.getUserType();
    String trainerUsername = user.getUsername();

    // Si el nuevo tipo es USER o el tipo actual es TRAINER, desasignar entrenadores
    if (newType == RoleEnum.USER || currentType == RoleEnum.TRAINER) {
        List<Users> users = userService.findAllUsers();
        for (Users u : users) {
            Users assignedTrainer = u.getTrainer();
            if (assignedTrainer != null && trainerUsername.equals(assignedTrainer.getUsername())) {
                u.setTrainer(null);
                userService.save(u);
            }
        }
    }

    user.setUserType(newType);
    userService.save(user);

    return "redirect:/user/profiles";
}

    @GetMapping("/{username}/settings")
    public String showSettingsForm(@PathVariable("username") String username) {
        log.info("Accessing the user settings page");
        return "pages/user/userSettingsForm";
    }

}
