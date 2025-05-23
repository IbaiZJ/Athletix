package com.athletix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athletix.model.Notifications;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Integer> {
}
