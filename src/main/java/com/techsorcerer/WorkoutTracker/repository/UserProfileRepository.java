package com.techsorcerer.WorkoutTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsorcerer.WorkoutTracker.entity.ProfileEntity;

public interface UserProfileRepository extends JpaRepository<ProfileEntity, Long> {

	Optional<ProfileEntity> findByUser_UserId(String userId);

}
