package com.techsorcerer.WorkoutTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSessionEntity, Long> {

}
