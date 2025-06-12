package com.athletix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.athletix.model.Users;

import com.athletix.model.UsersTypes;

public interface UsersTypesRepository extends JpaRepository<UsersTypes, Long> {
    Optional<UsersTypes> findByDescription(String description);
    List<UsersTypes> findAll();
}
