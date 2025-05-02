package com.techsorcerer.WorkoutTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSession;
import com.techsorcerer.WorkoutTracker.workoutService.WorkoutService;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
	
	@Autowired
	WorkoutService workoutService;
	
	
	public WorkoutSession createWorkout(@RequestBody WorkoutSessionDto workoutSessionDto) {
		return workoutService.createWorkout(workoutSessionDto);
	}

}
