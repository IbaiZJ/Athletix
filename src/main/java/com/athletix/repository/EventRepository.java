package com.athletix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athletix.model.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, Integer> {
    // @Query("SELECT NEW com.athletix.model.DTO.EventCardDTO(" +
    // "e.id, e.title, e.shortDescription, e.date, e.location) " +
    // "FROM Event e " +
    // "WHERE e.date >= CURRENT_DATE")
    // List<EventCardDTO> findAllActiveEventsAsCardDTO();

    // @Query("SELECT NEW com.athletix.model.DTO.EventCardDTO(" +
    //         "e.id, e.title, e.shortDescription, e.date, e.location) " +
    //         "FROM com.athletix.model.Events e " +
    //         "WHERE e.id NOT IN (" +
    //         "    SELECT ue.event.id FROM UsersEvents ue WHERE ue.user = :user" +
    //         ")")
    // List<EventCardDTO> findAvailableEventsForUser(@Param("user") Users user);
}
