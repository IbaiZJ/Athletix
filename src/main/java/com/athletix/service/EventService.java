package com.athletix.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.enums.EventRoleEnum;
import com.athletix.model.DTO.EventDTO;
import com.athletix.model.DTO.EventParticipantsDTO;
import com.athletix.model.DTO.EventRegistrationDTO;
import com.athletix.model.Events;
import com.athletix.model.Users;
import com.athletix.model.UsersEvents;
import com.athletix.repository.EventRepository;
import com.athletix.repository.UserEventRepository;

import jakarta.transaction.Transactional;

@Service
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final UserEventRepository userEventRepository;

    public EventService(
            EventRepository eventRepository,
            UserEventRepository userEventRepository) {
        this.eventRepository = eventRepository;
        this.userEventRepository = userEventRepository;
        log.info("TrackingService initialized");
    }

    public Events getEventById(Integer id) {
        log.info("Fetching event with id: {}", id);
        return eventRepository.findById(id).orElse(null);
    }

    @Transactional
    public void createEvent(Users user, EventRegistrationDTO eventDTO) {
        // Create event
        Events event = eventDTO.toEntity();
        eventRepository.save(event);

        // Create N-N
        UsersEvents userEvent = new UsersEvents();
        userEvent.setRole(EventRoleEnum.CREATOR);
        userEvent.setRegistrationDate(LocalDateTime.now());
        userEvent.setUser(user);
        userEvent.setEvent(event);
        userEventRepository.save(userEvent);

        log.info("Created new event with title: {} for user: {}", event.getTitle(), user.getUsername());
    }

    @Transactional
    public List<EventDTO> getRegisteredEvents(Users user) {
        log.info("Fetching registered events for user: {}", user.getUsername());
        return userEventRepository.findRegisteredEventsByUser(user);
    }

    @Transactional
    public List<EventDTO> getAvailableEvents(Users user) {
        log.info("Fetching available events for user: {}", user.getUsername());
        return userEventRepository.findAvailableEventsForUser(user);
    }

    @Transactional
    public List<EventDTO> getMyEvents(Users user) {
        log.info("Fetching events created by user: {}", user.getUsername());
        return userEventRepository.findEventsCreatedByUser(user);
    }

    @Transactional
    public List<EventDTO> getFriendsEvents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFriendsEvents'");
    }

    @Transactional
    public List<EventParticipantsDTO> getEventParticipants(Integer eventId) {
        List<UsersEvents> usersEvents = userEventRepository.findByEventId(eventId);

        return usersEvents.stream()
                .map(this::mapToEventParticipantsDTO)
                .collect(Collectors.toList());
    }

    private EventParticipantsDTO mapToEventParticipantsDTO(UsersEvents usersEvent) {
        EventParticipantsDTO dto = new EventParticipantsDTO();
        Users user = usersEvent.getUser();

        dto.setUsername(user.getUsername());
        dto.setFullName(user.getName() + " " + user.getSurname());
        dto.setProfileImage(user.getProfileImage());
        dto.setRole(usersEvent.getRole());
        dto.setMail(user.getEmail());
        dto.setPhoneNumber(user.getPhone());
        dto.setRegistrationDate(usersEvent.getRegistrationDate());

        return dto;
    }

    public Integer getParticipantsCount(Integer eventId) {
        return userEventRepository.countByEventId(eventId);
    }

    public Events findById(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Integer findCreatorIdByEventId(Integer eventId) {
        return userEventRepository.findCreatorIdByEventId(eventId);
    }

    @Transactional
    public void updateEvent(Users user, EventRegistrationDTO eventDTO) {
        // Find existing event
        Events existingEvent = eventRepository.findById(eventDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        // TODO: Verify user has permission to edit (creator or admin)

        // Update event fields

        LocalDateTime dateTime = LocalDateTime.of(
                LocalDate.parse(eventDTO.getDate()),
                LocalTime.of(eventDTO.getDateH(), eventDTO.getDateM()));

        existingEvent.setTitle(eventDTO.getTitle());
        existingEvent.setShortDescription(eventDTO.getShortDescription());
        existingEvent.setDescription(eventDTO.getDescription());
        existingEvent.setDate(dateTime);
        existingEvent.setKm(eventDTO.getKm());
        existingEvent.setLocation(eventDTO.getLocation());
        existingEvent.setLatitude(eventDTO.getLatitude());
        existingEvent.setLongitude(eventDTO.getLongitude());
        existingEvent.setActivity(eventDTO.getActivity());
        existingEvent.setDifficulty(eventDTO.getDifficulty());
        existingEvent.setProfileImage(eventDTO.getProfileImageURL());

        eventRepository.save(existingEvent);
        log.info("Updated event with ID: {} by user: {}", eventDTO.getId(), user.getUsername());
    }

    public EventRoleEnum findUserRoleByEventId(Integer eventId, Integer userId) {
        EventRoleEnum role = userEventRepository.findUserRoleByEventIdAndUserId(eventId, userId);
        if (role == null) {
            return EventRoleEnum.PARTICIPANT;
        }
        return role;
    }

    @Transactional
    public void addUserToEvent(Users user, Integer eventId, EventRoleEnum role) {
        UsersEvents userEvent = new UsersEvents();
        userEvent.setRegistrationDate(LocalDateTime.now());
        userEvent.setUser(user);
        userEvent.setEvent(findById(eventId));
        userEvent.setRole(role);
        userEventRepository.save(userEvent);
    }

    @Transactional
    public void removeUserFromEvent(Users user, Integer eventId) {
        userEventRepository.deleteByUserAndEventId(user, eventId);
    }

    @Transactional
    public boolean existsByUserIdAndEventId(Integer userId, Integer eventId) {
        return userEventRepository.existsByUserIdAndEventId(userId, eventId);
    }

    @Transactional
    public void deleteEvent(Integer eventId) {
        userEventRepository.deleteByEventId(eventId);
        eventRepository.deleteById(eventId);
    }

     @Transactional
    public void deleteEventParticipant(Integer eventId, String username) {
        boolean exists = userEventRepository.findByEventIdAndUsername(eventId, username).isPresent();
        if (!exists) {
            throw new IllegalArgumentException("El usuario no está registrado en el evento");
        }

        userEventRepository.deleteByEventIdAndUsername(eventId, username);
    }

}
