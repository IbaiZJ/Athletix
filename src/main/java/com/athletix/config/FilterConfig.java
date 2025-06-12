package com.athletix.config;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.athletix.filter.NotificationFilter;
// import com.athletix.service.NotificationService;
// import com.athletix.service.UserService;

// @Configuration
// public class FilterConfig {

//     @Bean
//     public FilterRegistrationBean<NotificationFilter> notificationFilter(NotificationService notificationService, UserService userService) {
//         FilterRegistrationBean<NotificationFilter> registrationBean = new FilterRegistrationBean<>();
//         registrationBean.setFilter(new NotificationFilter(notificationService, userService));
//         registrationBean.addUrlPatterns("/*");
//         registrationBean.setOrder(1);
//         return registrationBean;
//     }
// }
