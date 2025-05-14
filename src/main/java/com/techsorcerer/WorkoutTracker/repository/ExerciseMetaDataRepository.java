package com.techsorcerer.WorkoutTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsorcerer.WorkoutTracker.entity.ExerciseMetaDataEntity;

public interface ExerciseMetaDataRepository extends JpaRepository<ExerciseMetaDataEntity, Long> {

	boolean existsByExerciseNameIgnoreCaseAndTargetAreaIgnoreCase(String exerciseName, String targetArea);

	List<ExerciseMetaDataEntity> findByTargetAreaIgnoreCase(String targetArea);

}
