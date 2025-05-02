package com.techsorcerer.WorkoutTracker.workoutService;

import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSession;


public interface WorkoutService {

	WorkoutSession createWorkout(WorkoutSessionDto workoutSessionDto);

}
