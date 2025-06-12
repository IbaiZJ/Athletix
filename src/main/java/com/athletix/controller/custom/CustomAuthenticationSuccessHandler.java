package com.athletix.controller.custom;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.athletix.model.DTO.UserSessionDTO;
import com.athletix.model.Users;
import com.athletix.repository.UserRepository;
import com.athletix.service.NotificationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public CustomAuthenticationSuccessHandler(
            UserRepository userRepository,
            NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        log.info("CustomAuthenticationSuccessHandler initialized");
    }

    // This is what happens when the user logs in successfully
    // "/login post"
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        Users user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (session != null) {
            // Set user information in the session
            UserSessionDTO userSessionDTO = new UserSessionDTO(user);
            session.setAttribute("user", userSessionDTO);

            log.info("User {} logged in. Session ID: {}", user.getUsername(), session.getId());
        }

        notificationService.reloadNotifications(request, user);

        response.sendRedirect("/home");
    }

}
