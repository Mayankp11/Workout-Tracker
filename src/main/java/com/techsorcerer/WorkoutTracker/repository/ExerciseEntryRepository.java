package com.techsorcerer.WorkoutTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techsorcerer.WorkoutTracker.entity.ExerciseEntryEntity;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;

@Repository
public interface ExerciseEntryRepository extends JpaRepository<ExerciseEntryEntity, Long> {
	Optional<ExerciseEntryEntity> findBySessionAndExerciseName(WorkoutSessionEntity session, String exerciseName);

}
