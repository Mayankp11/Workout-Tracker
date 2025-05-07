package com.techsorcerer.WorkoutTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.UpdateExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;

import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.GroupedExerciseEntryResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDatesResponse;
import com.techsorcerer.WorkoutTracker.service.WorkoutService;

@RestController
@RequestMapping("/api")
public class WorkoutController {

	@Autowired
	WorkoutService workoutService;

	@PostMapping("/addWorkout")
	public ApiResponse createWorkout(@RequestBody WorkoutSessionDto workoutSessionDto) {
		return workoutService.createWorkout(workoutSessionDto);
	}
	@PostMapping("/previousWorkout")
	public ApiResponse addWorkoutForPastDates(@RequestBody WorkoutSessionDto workoutSessionDto) {
		return workoutService.addWorkoutForPastDates(workoutSessionDto);
	}
	
	@GetMapping("/getWorkout")
	public ResponseEntity<List<GroupedExerciseEntryResponse>> getWorkoutByDate(@RequestParam("date") String dateStr) {
		List<GroupedExerciseEntryResponse> exercises = workoutService.getWorkoutForDate(dateStr);
		return ResponseEntity.ok(exercises);
	}
	
	@GetMapping("/workout-dates")
	public ResponseEntity<WorkoutDatesResponse> getWorkoutDates() {
	    WorkoutDatesResponse response = workoutService.getWorkoutDates();
	    return ResponseEntity.ok(response);
	}
	
	@PutMapping("/workout/updateExercise/{exerciseId}")
	public ApiResponse updateExercise(@PathVariable Long exerciseId, @RequestBody UpdateExerciseEntryDto exerciseEntryDto) {
		return workoutService.updateExerciseEntry(exerciseId, exerciseEntryDto);
		
	}
	
	@DeleteMapping("/workout/deleteExercise/{exerciseId}")
	public ApiResponse deleteWorkout(@PathVariable Long exerciseId) {
		return workoutService.deleteWorkout(exerciseId);
	}
}
