package com.techsorcerer.WorkoutTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsorcerer.WorkoutTracker.entity.ExerciseEntryEntity;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;

public interface ExerciseEntryRepository extends JpaRepository<ExerciseEntryEntity, Long> {
	Optional<ExerciseEntryEntity> findBySessionAndExerciseName(WorkoutSessionEntity session, String exerciseName);

}
