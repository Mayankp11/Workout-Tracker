package com.techsorcerer.WorkoutTracker.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSessionEntity, Long> {

	Optional<WorkoutSessionEntity> findByUserAndDate(UserEntity user, LocalDate today);

}
