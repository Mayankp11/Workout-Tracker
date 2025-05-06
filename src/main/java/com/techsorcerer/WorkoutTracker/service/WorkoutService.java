package com.techsorcerer.WorkoutTracker.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;


public interface WorkoutService {

	ApiResponse createWorkout(WorkoutSessionDto workoutSessionDto);

}
