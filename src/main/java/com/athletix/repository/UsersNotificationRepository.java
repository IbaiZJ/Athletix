package com.athletix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athletix.model.UsersNotifications;

@Repository
public interface UsersNotificationRepository extends JpaRepository<UsersNotifications, Integer> {
    // List<UsersNotifications> findByUserOrderByDateDesc(Users user);

}
