package com.athletix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.enums.FriendshipStatus;
import com.athletix.model.Friends;
import com.athletix.model.Users;
import com.athletix.repository.FriendRepository;
import com.athletix.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FriendService {
    private static final Logger log = LoggerFactory.getLogger(FriendService.class);

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public FriendService(
            UserRepository userRepository,
            FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @Transactional
    public void createFriends(Integer user1Id, Integer user2Id) {
        if (user1Id.equals(user2Id)) {
            throw new IllegalArgumentException("Cannot create friendship with yourself");
        }

        // Order users
        Integer smallerId = Math.min(user1Id, user2Id);
        Integer largerId = Math.max(user1Id, user2Id);

        Users user1 = userRepository.findById(smallerId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + smallerId));
        Users user2 = userRepository.findById(largerId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + largerId));

        // Verify if friendship already exists
        if (friendRepository.existsByUser1AndUser2(user1, user2)) {
            throw new IllegalStateException("Friendship already exists between these users");
        }

        // Crear la relación de amistad (solo un registro)
        Friends friendship = new Friends();
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendRepository.save(friendship);
    }

    public boolean areFriends(String username1, String username2) {
        if (username1.equals(username2)) {
            return false;
        }
        if (!userRepository.existsByUsername(username1) ||
                !userRepository.existsByUsername(username2)) {
            return false;
        }

        return friendRepository.existsFriendshipBetweenUsers(username1, username2);
    }
}
