package com.athletix.util;

import java.util.List;
import java.util.Set;

import com.athletix.model.Images;
import com.athletix.model.Users;
import com.athletix.model.DTO.UserCardDTO;

public class UserValidationUtil {
    private static final Set<String> FORBIDDEN_USERNAMES = Set.of(
            "admin", "administrator", "root", "superuser", "index", "login",
            "register", "signup", "signin", "logout", "signout", "home", "dashboard",
            "profile", "settings", "configuration", "preferences", "account", "user",
            "users", "adminpanel", "homepage", "main", "default", "welcome", "about",
            "contact", "services", "create-account", "profiles", "create", "edit", "delete");

    public static void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio");
        }

        if (FORBIDDEN_USERNAMES.contains(username.toLowerCase())) {
            throw new IllegalArgumentException("El nombre de usuario no es válido");
        }
    }

    public static void validatePassword(String password, String repeatPassword) {
        if (password == null)
            throw new IllegalArgumentException("La contraseña no puede ser nula");
        if (password.length() < 8)
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        if (!password.matches(".*[A-Z].*"))
            throw new IllegalArgumentException("La contraseña debe tener al menos 1 mayúscula");
        if (!password.matches(".*[a-z].*"))
            throw new IllegalArgumentException("La contraseña debe tener al menos 1 minúscula");
        if (!password.matches(".*[0-9].*"))
            throw new IllegalArgumentException("La contraseña debe tener al menos 1 número");
        if (!password.equals(repeatPassword))
            throw new IllegalArgumentException("Las contraseñas no coinciden");
    }
    public static UserCardDTO toUserCardDTO(Users user, List<Images> images) {
        return new UserCardDTO(
                user.getUsername(),
                user.getName() + " " + user.getSurname(),
                user.getProfileImage(),
                user.getTrainer(),
                user.getEmail(),
                user.getHeight(),
                user.getWeight(),
                user.getGender(),
                user.getUserType()
        );
    }
}
