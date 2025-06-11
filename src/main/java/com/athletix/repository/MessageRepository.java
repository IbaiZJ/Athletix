package com.athletix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athletix.model.Friends;
import com.athletix.model.Messages;

public interface MessageRepository extends JpaRepository<Messages, Integer> {
    @Query("""
                SELECT m FROM Messages m
                WHERE m.friendship.id = :friendshipId
                ORDER BY m.date DESC
                LIMIT 1
            """)
    Messages findLastMessageByFriendshipId(@Param("friendshipId") Integer friendshipId);

    List<Messages> findByFriendshipOrderByDateAsc(Friends friendship);
}
