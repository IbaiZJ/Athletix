package com.athletix.service.custom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.athletix.model.Users;
import com.athletix.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String role = null; // user.getRole();
        if (role == null || role.trim().isEmpty()) {
            role = "ROLE_USER";
        } 
        // else if (!role.startsWith("ROLE_")) {
        //     role = "ROLE_" + role.toUpperCase();
        // }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(role)));
        // new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())
    }

}
