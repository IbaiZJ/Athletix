package com.athletix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athletix.model.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, Integer> {

}
