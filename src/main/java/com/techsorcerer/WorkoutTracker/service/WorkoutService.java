package com.techsorcerer.WorkoutTracker.service;

import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSession;


public interface WorkoutService {

	WorkoutSession createWorkout(WorkoutSessionDto workoutSessionDto);

}
