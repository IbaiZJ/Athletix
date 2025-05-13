package com.athletix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // https://www.baeldung.com/spring-boot-security-autoconfiguration
    // https://spring.io/guides/gs/securing-web

    private final String[] PATH_WHITELIST = {
            "/",
            "/css/**",
            "/js/headerNav.js", // ||| botoira bakarrik sartu ahal izateko
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
                        // .defaultSuccessUrl("/", true) // redirect
                        .permitAll())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}
