package com.athletix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athletix.enums.EventRoleEnum;
import com.athletix.model.DTO.EventDTO;
import com.athletix.model.Users;
import com.athletix.model.UsersEvents;

public interface UserEventRepository extends JpaRepository<UsersEvents, Integer> {

    @Query("SELECT NEW com.athletix.model.DTO.EventDTO(" +
            "e.id, e.title, e.shortDescription, e.description, e.date, e.km, e.location, " +
            "e.latitude, e.longitude, e.activity, e.difficulty, e.profileImage, " +
            "(SELECT COUNT(ue2) FROM UsersEvents ue2 WHERE ue2.event = e)) " +
            "FROM UsersEvents ue " +
            "JOIN ue.event e " +
            "WHERE ue.user = :user")
    List<EventDTO> findRegisteredEventsByUser(@Param("user") Users user);

    @Query("SELECT NEW com.athletix.model.DTO.EventDTO(" +
            "e.id, e.title, e.shortDescription, e.description, e.date, e.km, e.location, " +
            "e.latitude, e.longitude, e.activity, e.difficulty, e.profileImage, " +
            "(SELECT COUNT(ue2) FROM UsersEvents ue2 WHERE ue2.event = e)) " +
            "FROM com.athletix.model.Events e " +
            "WHERE e NOT IN (" +
            "    SELECT ue.event FROM UsersEvents ue WHERE ue.user = :user" +
            ") AND e.date >= CURRENT_DATE")
    List<EventDTO> findAvailableEventsForUser(@Param("user") Users user);

    @Query("SELECT NEW com.athletix.model.DTO.EventDTO(" +
            "e.id, e.title, e.shortDescription, e.description, e.date, e.km, e.location, " +
            "e.latitude, e.longitude, e.activity, e.difficulty, e.profileImage, " +
            "(SELECT COUNT(ue2) FROM UsersEvents ue2 WHERE ue2.event = e)) " +
            "FROM UsersEvents ue " +
            "JOIN ue.event e " +
            "WHERE ue.user = :user AND ue.role = com.athletix.enums.EventRoleEnum.CREATOR")
    List<EventDTO> findEventsCreatedByUser(@Param("user") Users user);

    List<UsersEvents> findByEventId(Integer eventId);

    List<UsersEvents> findByEventIdAndRole(Integer eventId, EventRoleEnum role);

    Integer countByEventId(Integer eventId);

}
