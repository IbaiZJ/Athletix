package com.athletix.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.athletix.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
