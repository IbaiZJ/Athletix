package com.athletix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.athletix.model.Users;
import java.util.List;


public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
    List<Users> findAll();
}

