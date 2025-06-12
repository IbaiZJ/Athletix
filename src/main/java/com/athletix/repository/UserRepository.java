package com.athletix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.athletix.model.Users;


public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
    List<Users> findAll();
    @Query("SELECT u, COALESCE(SUM(t.km), 0) as totalDistance " +
           "FROM Users u LEFT JOIN u.trackings t " +
           "GROUP BY u.id " +
           "ORDER BY totalDistance DESC")
    List<Object[]> findAllUsersOrderByDistance();
    
}

