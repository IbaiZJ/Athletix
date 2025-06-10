package com.athletix.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athletix.enums.NotificationEnum;
import com.athletix.model.DTO.ExerciseRegistrationDTO;
import com.athletix.model.DTO.NotificationRegistrationDTO;
import com.athletix.model.Exercises;
import com.athletix.model.Users;
import com.athletix.service.ExerciseService;
import com.athletix.service.NotificationService;
import com.athletix.service.UserService;
import com.athletix.util.ExerciseUtil;
import com.athletix.model.DTO.ExerciseCardDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {
    private static final Logger log = LoggerFactory.getLogger(ExerciseController.class);
    private static final String EXERCISE_LIST = "exercises";

    private final NotificationService notificationService;
    private final ExerciseService exerciseService;
    private final UserService userService;

    public ExerciseController(
            NotificationService notificationService,
            ExerciseService exerciseService,
            UserService userService) {
        this.notificationService = notificationService;
        this.exerciseService = exerciseService;
        this.userService = userService;
        log.info("ExerciseController initialized");
    }

    @GetMapping("")
    public String showMyExercises() {
        Users user = userService.getCurrentUser();
        log.info("Showing exercises for user: {}", user.getUsername());
        return "redirect:/exercise/" + user.getUsername();
    }

    @GetMapping("/{username}")
    public String showUserExercises(@PathVariable("username") String username, Model model) {
        Users user = userService.findByUsername(username);
        if (user == null) {
            log.error("User not found: {}", username);
            return "error/404";
        }

        List<Exercises> exercises = exerciseService.getExercisesByUser(user);
        List<ExerciseCardDTO> exerciseDTOs = new ArrayList<>();
        for (Exercises exercise : exercises) {
            exerciseDTOs.add(ExerciseUtil.toExerciseCardDTO(exercise,null));
        }
        model.addAttribute("exercises", exerciseDTOs);

        log.info("Exercises for user {}: {}", username, exercises);

        return "pages/exercise"; // Asegúrate de tener esta vista
    }

    @GetMapping("/{username}/create")
    @PreAuthorize("#username == authentication.name")
    public String createExerciseForm(@PathVariable("username") String username) {
        log.info("Create exercise page accessed");
        return "pages/exercise/exerciseForm";
    }

    @PostMapping("/{username}/create")
    @PreAuthorize("#username == authentication.name")
    public String createExercise(@PathVariable("username") String username, ExerciseRegistrationDTO dto,
            HttpServletRequest request) {

        Users creator = userService.getCurrentUser();

        log.info("Creating new exercise by {}", creator.getUsername());

        // Crear ejercicio sin asignar a ningún usuario todavía
        exerciseService.createExercise(creator, dto);

        log.info("Exercise created by {}", creator.getUsername());

        // Notificación para el creador
        NotificationRegistrationDTO notification = new NotificationRegistrationDTO(
                "Nuevo entrenamiento creado",
                "Has creado un nuevo entrenamiento: " + dto.getTitle(),
                NotificationEnum.CREATE_TRACKING);

        notificationService.createNotificationForUser(creator, notification);
        notificationService.reloadNotifications(request, creator);

        return "redirect:/exercise/" + username;
    }
       
    @GetMapping("/trainer")
    public String showTrainerExercises(Model model) {
        Users user = userService.getCurrentUser();
        Users trainer=user.getTrainer();
        if (trainer == null) {
        // Redirige a la misma página (o una por defecto)
        return "redirect:/home"; // O la que quieras
        }
        List<Exercises> exercises = exerciseService.getExercisesByUser(trainer);
        List<ExerciseCardDTO> exerciseDTOs = new ArrayList<>();

        for (Exercises exercise : exercises) {
            exerciseDTOs.add(ExerciseUtil.toExerciseCardDTO(exercise,null));
        }
        model.addAttribute(EXERCISE_LIST, exerciseDTOs);

        log.info("Exercises for user {}: {}", user.getUsername(), exercises);
         return "pages/userExercises"; // Asegúrate de tener esta vista
    }
}
