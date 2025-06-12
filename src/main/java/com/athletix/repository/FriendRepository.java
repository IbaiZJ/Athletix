package com.athletix.repository;

import java.util.List;
import java.util.Optional;

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
    boolean existsFriendshipBetweenUsers(@Param("user1") String user1, @Param("user2") String user2);

    @Query("SELECT f.id FROM Friends f " +
            "WHERE ((f.user1.username = :username1 AND f.user2.username = :username2) OR " +
            "(f.user1.username = :username2 AND f.user2.username = :username1)) " +
            "AND f.status = 'ACCEPTED'")
    Optional<Integer> findFriendshipIdBetweenUsers(
            @Param("username1") String username1,
            @Param("username2") String username2);

    @Query("SELECT f.id FROM Friends f " +
            "WHERE (f.user1.username = :user1 AND f.user2.username = :user2)")
    Optional<Integer> findFriendshipIdByUsers(@Param("user1") String user1, @Param("user2") String user2);

    @Query("""
            SELECT f FROM Friends f
            WHERE f.status = com.athletix.enums.FriendshipStatus.ACCEPTED
            AND (f.user1.id = :userId OR f.user2.id = :userId)
            """)
    List<Friends> findAcceptedFriendshipsByUserId(@Param("userId") Integer userId);

    boolean existsByUser1AndUser2(Users user1, Users user2);
    Optional<Friends> findByUser1AndUser2(Users user1, Users user2);
}
