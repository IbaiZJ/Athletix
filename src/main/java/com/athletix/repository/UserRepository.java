package com.athletix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.athletix.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
