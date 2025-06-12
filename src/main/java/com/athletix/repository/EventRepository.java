package com.athletix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.athletix.model.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, Integer> {
    // @Query("SELECT NEW com.athletix.model.DTO.EventDTO(" +
    // "e.id, e.title, e.shortDescription, e.date, e.location) " +
    // "FROM Event e " +
    // "WHERE e.date >= CURRENT_DATE")
    // List<EventDTO> findAllActiveEventsAsCardDTO();

    // @Query("SELECT NEW com.athletix.model.DTO.EventDTO(" +
    //         "e.id, e.title, e.shortDescription, e.date, e.location) " +
    //         "FROM com.athletix.model.Events e " +
    //         "WHERE e.id NOT IN (" +
    //         "    SELECT ue.event.id FROM UsersEvents ue WHERE ue.user = :user" +
    //         ")")
    // List<EventDTO> findAvailableEventsForUser(@Param("user") Users user);

    

}
