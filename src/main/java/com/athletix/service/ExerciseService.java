package com.athletix.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.athletix.model.DTO.ExerciseRegistrationDTO;
import com.athletix.model.Exercises;
import com.athletix.model.Users;
import com.athletix.repository.ExerciseRepository;

import jakarta.transaction.Transactional;

@Service
public class ExerciseService {
    private static final Logger log = LoggerFactory.getLogger(ExerciseService.class);

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        log.info("ExerciseService initialized");
    }

    @Transactional
    public List<Exercises> getExercisesByUser(Users user) {
        log.info("Fetching exercises created by user: {}", user.getUsername());
        return exerciseRepository.findByCreatorOrderByDateDesc(user);
    }

    @Transactional
    public void createExercise(Users creator, ExerciseRegistrationDTO dto) {
        Exercises exercise = dto.toEntity(creator);
        exerciseRepository.save(exercise);
    }

}
