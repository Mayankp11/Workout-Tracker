package com.techsorcerer.WorkoutTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;
import com.techsorcerer.WorkoutTracker.service.UserService;
import com.techsorcerer.WorkoutTracker.service.WorkoutService;

@RestController
@RequestMapping("/api")
public class WorkoutController {
	
	
	
	@Autowired
	WorkoutService workoutService;
	
	@PostMapping("/workout")
	public WorkoutSessionEntity createWorkout(@RequestBody WorkoutSessionDto workoutSessionDto) {
		return workoutService.createWorkout(workoutSessionDto);
	}

}
