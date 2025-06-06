package com.athletix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.athletix.model.DTO.UserRegistrationDTO;
import com.athletix.model.Users;
import com.athletix.repository.UserRepository;
import com.athletix.repository.UsersTypesRepository;
import com.athletix.util.UserValidationUtil;

import jakarta.transaction.Transactional;

  
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UsersTypesRepository usersTypesRepository;
    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        log.info("UserService initialized");
    }

    @Transactional
    public void registerUser(UserRegistrationDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        UserValidationUtil.validateUsername(userDTO.getUsername());
        UserValidationUtil.validatePassword(userDTO.getPassword(), userDTO.getRepeatPassword());

        Users user = userDTO.toEntity(passwordEncoder);
        user.setUserType(usersTypesRepository.findByDescription("USER")
        .orElseThrow(() -> new IllegalStateException("Tipo de usuario 'Trainer' no existe en la base de datos")));

        userRepository.save(user);

        log.info("User saved: {}", userDTO.getUsername());
    }

    @Transactional
    public Users findByUsername(String username) {
        if (username == null || username.isEmpty()) {
            log.warn("Username is null or empty");
            return null;
        }
        log.info("Searching for user by username: {}", username);
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    @Transactional
    public Users getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else if (principal != null) {
            username = principal.toString();
        } else {
            log.warn("No principal found in security context");
            return null;
        }
        return findByUsername(username);
    }

}
