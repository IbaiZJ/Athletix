package com.athletix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.athletix.custom.CustomLoginSuccessHandler;
import com.athletix.model.User;
import com.athletix.repository.UserRepository;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password) {
        // 1. User exists?
        if (userRepository.findByUsername(username).isPresent()) {
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
            if (username.equalsIgnoreCase(forbidden)) {
                log.info("El nombre de usuario no es válido");
                throw new IllegalArgumentException("El nombre de usuario no es válido");
            }
        }

        // 3. Check username and password
        if (username == null || username.trim().isEmpty()) {
            log.info("El nombre de usuario y contraseña es obligatorio");
            throw new IllegalArgumentException("El nombre de usuario y contraseña es obligatorio");
        }

        if (!isValidPassword(password)) {
            log.info("La contraseña no es válida");
            throw new IllegalArgumentException("La contraseña no es válida");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        log.info("User saved: {}", username);
        userRepository.save(user);
    }

    private boolean isValidPassword(String password) {
        if (password == null)
            return false;
        if (password.length() < 8)
            return false;
        // At lieast 1 uppercase
        if (!password.matches(".*[A-Z].*"))
            return false;
        // At lieast 1 lowercase
        if (!password.matches(".*[a-z].*"))
            return false;
        // At least 1 number
        return password.matches(".*[0-9].*");
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

}
