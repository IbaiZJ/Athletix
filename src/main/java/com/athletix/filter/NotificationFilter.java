package com.athletix.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athletix.model.Users;
import com.athletix.service.NotificationService;
import com.athletix.service.UserService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class NotificationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(NotificationFilter.class);

    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationFilter(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            Users currentUser = userService.getCurrentUser();
            if (currentUser != null) {
                notificationService.reloadNotifications(httpRequest, currentUser);
                // log.info("Reloaded notifications for user {}", currentUser.getUsername());
            }
        } catch (Exception e) {
            log.error("Error loading notifications", e);
        }

        chain.doFilter(request, response);
    }
}
