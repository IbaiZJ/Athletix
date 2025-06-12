package com.athletix.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.enums.FriendshipStatus;
import com.athletix.model.DTO.ChatMessage;
import com.athletix.model.DTO.ChatUserDTO;
import com.athletix.model.DTO.UserSessionDTO;
import com.athletix.model.Friends;
import com.athletix.model.Messages;
import com.athletix.model.Users;
import com.athletix.repository.FriendRepository;
import com.athletix.repository.MessageRepository;
import com.athletix.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FriendService {
    private static final Logger log = LoggerFactory.getLogger(FriendService.class);

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public FriendService(
            UserRepository userRepository,
            FriendRepository friendRepository,
            MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
        this.messageRepository = messageRepository;
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

    @Transactional
    public void createFriends(Users user1, Users user2) {
        if (user1.getId() > user2.getId()) {
            Users temp = user1;
            user1 = user2;
            user2 = temp;
        }

        // Verify if friendship already exists
        if (friendRepository.existsByUser1AndUser2(user1, user2)) {
            throw new IllegalStateException("Friendship already exists between these users");
        }

        Friends friendship = new Friends();
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendRepository.save(friendship);
    }

    @Transactional
    public void removeFriendship(Users user1, Users user2) {
        if (user1.getId() > user2.getId()) {
            Users temp = user1;
            user1 = user2;
            user2 = temp;
        }

        Friends friendship = friendRepository.findByUser1AndUser2(user1, user2)
                .orElseThrow(() -> new IllegalStateException("No friendship found between these users"));

        friendRepository.delete(friendship);
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

    public Optional<Friends> findById(Integer id) {
        return friendRepository.findById(id);
    }

    @Transactional
    public Integer getFriendsId(String username1, String username2) {
        // 1. Validación básica
        if (username1.equals(username2)) {
            throw new IllegalArgumentException("Cannot check friendship with yourself");
        }

        // 2. Verificar existencia de usuarios
        Users user1 = userRepository.findByUsername(username1)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username1));
        Users user2 = userRepository.findByUsername(username2)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username2));

        // 3. Buscar amistad (en cualquier dirección) y con estado ACCEPTED
        return friendRepository.findFriendshipIdBetweenUsers(username1, username2)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Users %s and %s are not friends or friendship not accepted",
                                username1, username2)));
    }

    public List<ChatUserDTO> getFriendChatDTOs(Principal principal) {
        if (principal == null || principal.getName() == null) {
            throw new IllegalStateException("Usuario no autenticado");
        }

        String username = principal.getName();
        Users currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + username));

        List<Friends> friendships = friendRepository.findAcceptedFriendshipsByUserId(currentUser.getId());

        return friendships.stream()
                .map(friendship -> {
                    Users friend = friendship.getUser1().getId().equals(currentUser.getId())
                            ? friendship.getUser2()
                            : friendship.getUser1();

                    ChatUserDTO dto = new ChatUserDTO();
                    dto.setUsername(friend.getUsername());
                    dto.setName(friend.getName());
                    dto.setSurname(friend.getSurname());
                    dto.setProfileImage(friend.getProfileImage());

                    Messages lastMsg = messageRepository.findLastMessageByFriendshipId(friendship.getId());

                    if (lastMsg != null) {
                        ChatMessage chatMessage = new ChatMessage();
                        chatMessage.setSender(lastMsg.getSender().getUsername());
                        chatMessage.setRecipient(
                                friend.getUsername().equals(lastMsg.getSender().getUsername())
                                        ? currentUser.getUsername()
                                        : friend.getUsername());
                        chatMessage.setContent(lastMsg.getMessage());
                        chatMessage.setTimestamp(lastMsg.getDate());
                        dto.setLastMessage(chatMessage);
                    }

                    return dto;
                })
                .sorted((dto1, dto2) -> {
                    LocalDateTime t1 = dto1.getLastMessage() != null ? dto1.getLastMessage().getTimestamp()
                            : LocalDateTime.MIN;
                    LocalDateTime t2 = dto2.getLastMessage() != null ? dto2.getLastMessage().getTimestamp()
                            : LocalDateTime.MIN;
                    return t2.compareTo(t1);
                })
                .toList();

    }

    public List<UserSessionDTO> getFriendsDTOs(Principal principal) {
        if (principal == null || principal.getName() == null) {
            throw new IllegalStateException("Usuario no autenticado");
        }

        String username = principal.getName();
        Users currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + username));

        List<Friends> friendships = friendRepository.findAcceptedFriendshipsByUserId(currentUser.getId());

        return friendships.stream()
                .map(friendship -> {
                    Users friend = friendship.getUser1().getId().equals(currentUser.getId())
                            ? friendship.getUser2()
                            : friendship.getUser1();
                    return new UserSessionDTO(friend);
                })
                .toList();
    }

    public List<UserSessionDTO> getNonFriendUsersDTO(Principal principal) {
        if (principal == null || principal.getName() == null) {
            throw new IllegalStateException("Usuario no autenticado");
        }

        String username = principal.getName();
        Users currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + username));

        List<Friends> friendships = friendRepository.findAcceptedFriendshipsByUserId(currentUser.getId());

        Set<Integer> friendIds = friendships.stream()
                .map(f -> f.getUser1().getId().equals(currentUser.getId()) ? f.getUser2().getId()
                        : f.getUser1().getId())
                .collect(Collectors.toSet());

        friendIds.add(currentUser.getId());

        List<Users> nonFriends = userRepository.findAll().stream()
                .filter(user -> !friendIds.contains(user.getId()))
                .toList();

        return nonFriends.stream()
                .map(UserSessionDTO::new)
                .toList();
    }

}
