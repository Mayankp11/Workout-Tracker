package com.techsorcerer.WorkoutTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsorcerer.WorkoutTracker.entity.TargetAreaEntity;

public interface TargetAreaRepository extends JpaRepository<TargetAreaEntity, Long> {

}
