package com.athletix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athletix.model.Exercises;
import com.athletix.model.Users;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercises, Integer> {
    List<Exercises> findByCreatorOrderByDateDesc(Users creator);

    // @Query("SELECT t FROM Trackings t JOIN FETCH t.user WHERE t.user = :user")
    // List<Trackings> findByUserWithUser(@Param("user") Users user);
}
