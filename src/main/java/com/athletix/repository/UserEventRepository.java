package com.athletix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athletix.model.DTO.EventCardDTO;
import com.athletix.model.Users;
import com.athletix.model.UsersEvents;

public interface UserEventRepository extends JpaRepository<UsersEvents, Integer> {
    @Query("SELECT NEW com.athletix.model.DTO.EventCardDTO(" +
            "e.id, e.title, e.shortDescription, e.date, e.location) " +
            "FROM UsersEvents ue " +
            "JOIN ue.event e " +
            "WHERE ue.user = :user")
    List<EventCardDTO> findRegisteredEventsByUser(@Param("user") Users user);
}
