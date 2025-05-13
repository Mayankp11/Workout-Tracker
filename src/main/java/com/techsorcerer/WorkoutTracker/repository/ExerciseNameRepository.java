package com.techsorcerer.WorkoutTracker.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsorcerer.WorkoutTracker.dto.TargetAreaDto;
import com.techsorcerer.WorkoutTracker.entity.ExerciseNameEntity;

public interface ExerciseNameRepository extends JpaRepository<ExerciseNameEntity, Long> {

	Collection<TargetAreaDto> findByTargetAreaId(Long targetAreaId);

}
