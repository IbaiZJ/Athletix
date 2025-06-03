package com.athletix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.athletix.model.Friends;
import com.athletix.model.Users;

public interface FriendRepository extends JpaRepository<Friends, Integer> {
    boolean existsByUserAndFriend(Users user, Users friend);
}
