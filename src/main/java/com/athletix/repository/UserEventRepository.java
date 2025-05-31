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

    @Query("SELECT NEW com.athletix.model.DTO.EventCardDTO(" +
            "e.id, e.title, e.shortDescription, e.date, e.location) " +
            "FROM com.athletix.model.Events e " +
            "WHERE e NOT IN (" +
            "    SELECT ue.event FROM UsersEvents ue WHERE ue.user = :user" +
            ") AND e.date >= CURRENT_DATE")
    List<EventCardDTO> findAvailableEventsForUser(@Param("user") Users user);

    @Query("SELECT NEW com.athletix.model.DTO.EventCardDTO(" +
            "e.id, e.title, e.shortDescription, e.date, e.location) " +
            "FROM UsersEvents ue " +
            "JOIN ue.event e " +
            "WHERE ue.user = :user AND ue.role = com.athletix.enums.EventRoleEnum.CREATOR")
    List<EventCardDTO> findEventsCreatedByUser(@Param("user") Users user);
}
