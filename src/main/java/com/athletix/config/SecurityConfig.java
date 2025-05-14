package com.athletix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PATH_WHITELIST = {
            "/",
            "/about",
            "/contact",
            "/services",
            "/create-account",
            "/css/**",
            "/js/headerNav.js",
            "/img/**",
            "/svg/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(PATH_WHITELIST).permitAll()
                .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        // .defaultSuccessUrl("/", true) // redirect
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout((logout) -> logout
                        // .logoutSuccessUrl("/login?logout=true")
                        .permitAll());

        return http.build();
    }

}
