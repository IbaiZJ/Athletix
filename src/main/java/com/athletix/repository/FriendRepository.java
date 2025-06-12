package com.athletix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athletix.model.Friends;
import com.athletix.model.Users;

public interface FriendRepository extends JpaRepository<Friends, Integer> {
    @Query("SELECT COUNT(f) > 0 FROM Friends f WHERE " +
            "(f.user1.username = :user1 AND f.user2.username = :user2) OR " +
            "(f.user1.username = :user2 AND f.user2.username = :user1) AND " +
            "f.status = 'ACCEPTED'")
    boolean existsFriendshipBetweenUsers(@Param("user1") String user1,
            @Param("user2") String user2);

    boolean existsByUser1AndUser2(Users user1, Users user2);
}
