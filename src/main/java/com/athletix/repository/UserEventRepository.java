package com.athletix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athletix.enums.EventRoleEnum;
import com.athletix.model.DTO.EventDTO;
import com.athletix.model.Users;
import com.athletix.model.UsersEvents;

import jakarta.transaction.Transactional;

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

    @Query("SELECT ue.user.id FROM UsersEvents ue " +
            "WHERE ue.event.id = :eventId AND ue.role = com.athletix.enums.EventRoleEnum.CREATOR")
    Integer findCreatorIdByEventId(@Param("eventId") Integer eventId);

    @Query("SELECT ue.role FROM UsersEvents ue WHERE ue.event.id = :eventId AND ue.user.id = :userId")
    EventRoleEnum findUserRoleByEventIdAndUserId(@Param("eventId") Integer eventId,
            @Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UsersEvents ue WHERE ue.user = :user AND ue.event.id = :eventId")
    void deleteByUserAndEventId(@Param("user") Users user, @Param("eventId") Integer eventId);

    @Query("SELECT COUNT(ue) > 0 FROM UsersEvents ue WHERE ue.user.id = :userId AND ue.event.id = :eventId")
    boolean existsByUserIdAndEventId(@Param("userId") Integer userId, @Param("eventId") Integer eventId);

    @Modifying
    @Query("DELETE FROM UsersEvents ue WHERE ue.event.id = :eventId")
    void deleteByEventId(@Param("eventId") Integer eventId);

    List<UsersEvents> findByEventId(Integer eventId);

    List<UsersEvents> findByEventIdAndRole(Integer eventId, EventRoleEnum role);

    Integer countByEventId(Integer eventId);

    @Query("SELECT ue FROM UsersEvents ue WHERE ue.event.id = :eventId AND ue.user.username = :username")
    Optional<UsersEvents> findByEventIdAndUsername(@Param("eventId") Integer eventId,
            @Param("username") String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM UsersEvents ue WHERE ue.event.id = :eventId AND ue.user.username = :username")
    void deleteByEventIdAndUsername(@Param("eventId") Integer eventId, @Param("username") String username);

}
