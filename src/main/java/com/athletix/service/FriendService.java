package com.athletix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athletix.model.Friends;
import com.athletix.model.Users;
import com.athletix.repository.FriendRepository;
import com.athletix.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

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
    public void createFriends(Integer userId, Integer friendId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Users friend = userRepository.findById(friendId)
                .orElseThrow(() -> new EntityNotFoundException("Friend not found with id: " + friendId));

        if (friendRepository.existsByUserAndFriend(user, friend) ||
                friendRepository.existsByUserAndFriend(friend, user)) {
            throw new IllegalStateException("Friendship already exists between these users");
        }

        // Create a new chat for the friends
        // FriendsChats chat = new FriendsChats();
        // chat = friendsChatsRepository.save(chat);

        // Create the friendship relationship from user to friend
        Friends userToFriend = new Friends();
        userToFriend.setUser(user);
        userToFriend.setFriend(friend);
        // userToFriend.setChat(chat);
        friendRepository.save(userToFriend);

        // Create the reciprocal friendship relationship from friend to user
        Friends friendToUser = new Friends();
        friendToUser.setUser(friend);
        friendToUser.setFriend(user);
        // friendToUser.setChat(chat);
        friendRepository.save(friendToUser);
    }

}
