package com.athletix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.athletix.model.DTO.UserRegistrationDTO;
import com.athletix.model.User;
import com.athletix.repository.UserRepository;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationDTO user) {
        // 1. User exists?
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            log.info("El nombre de usuario ya está en uso");
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        // 2. User cant have some names
        final String[] FORBIDDEN_USERNAMES = { "admin", "administrator", "root", "superuser", "index", "login",
                "register", "signup", "signin", "logout", "signout", "home", "dashboard", "profile", "settings",
                "configuration", "preferences", "account", "user", "users", "adminpanel", "adminpanel",
                "adminpanel", "homepage", "index", "main", "default", "welcome", "about", "contact", "services",
                "create-account", "user", "profile", "profiles" };
        for (String forbidden : FORBIDDEN_USERNAMES) {
            if (user.getUsername().equalsIgnoreCase(forbidden)) {
                log.info("El nombre de usuario no es válido");
                throw new IllegalArgumentException("El nombre de usuario no es válido");
            }
        }

        // 3. Check username and password
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            log.info("El nombre de usuario y contraseña es obligatorio");
            throw new IllegalArgumentException("El nombre de usuario y contraseña es obligatorio");
        }

        String isValidPassword = isValidPassword(user.getPassword(), user.getRepeatPassword());
        if (isValidPassword != null) {
            throw new IllegalArgumentException(isValidPassword);
        }

        // Save user
        User createUser = new User();
        createUser.setUsername(user.getUsername());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createUser.setEmail(user.getEmail());
        createUser.setName(user.getName());
        createUser.setSurname(user.getSurname());
        createUser.setSurname2(user.getSurname2());
        createUser.setGender(user.getGender());
        createUser.setTown(user.getTown());
        createUser.setHeight(user.getHeight());
        createUser.setWeight(user.getWeight());
        createUser.setPhone(user.getPhone());
        createUser.setPhone(user.getBirthDate());
        createUser.setProfileImage(user.getProfileImageURL());

        log.info("User saved: {}", user.getUsername());
        userRepository.save(createUser);
    }

    private String isValidPassword(String password, String repeatPassword) {
        if (password == null)
            return "La contraseña no puede ser nula";
        if (password.length() < 8)
            return "La contraseña debe tener al menos 8 caracteres";
        // At least 1 uppercase
        if (!password.matches(".*[A-Z].*"))
            return "La contraseña debe tener al menos 1 mayúscula";
        // At least 1 lowercase
        if (!password.matches(".*[a-z].*"))
            return "La contraseña debe tener al menos 1 minúscula";
        // At least 1 number
        if(!password.matches(".*[0-9].*"))
            return "La contraseña debe tener al menos 1 número";
        // 2 passwords must be equal
        if(!password.equals(repeatPassword))
            return "Las contraseñas no coinciden";
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

}
