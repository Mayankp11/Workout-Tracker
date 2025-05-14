package com.techsorcerer.WorkoutTracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techsorcerer.WorkoutTracker.entity.ExerciseMetaDataEntity;
import com.techsorcerer.WorkoutTracker.response.TargetAreaWithCount;

public interface ExerciseMetaDataRepository extends JpaRepository<ExerciseMetaDataEntity, Long> {

	boolean existsByExerciseNameIgnoreCaseAndTargetAreaIgnoreCase(String exerciseName, String targetArea);

	List<ExerciseMetaDataEntity> findByTargetAreaIgnoreCase(String targetArea);

	@Query("SELECT DISTINCT e.targetArea FROM ExerciseMetaDataEntity e")
	List<String> findDistinctTargetArea();

	@Query("SELECT new com.techsorcerer.WorkoutTracker.response.TargetAreaWithCount(COUNT(e),e.targetArea) " +
		       "FROM ExerciseMetaDataEntity e GROUP BY e.targetArea")
		List<TargetAreaWithCount> getTargetAreasWithCounts();

	Optional<ExerciseMetaDataEntity> findByExerciseNameIgnoreCaseAndTargetAreaIgnoreCase(String exerciseName, String targetArea);


}
