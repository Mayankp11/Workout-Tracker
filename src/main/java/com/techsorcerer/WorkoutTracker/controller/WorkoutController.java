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

import com.techsorcerer.WorkoutTracker.dto.CardioEntryDto;
import com.techsorcerer.WorkoutTracker.dto.ExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.UpdateCardioEntryDto;
import com.techsorcerer.WorkoutTracker.dto.UpdateExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;

import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.GroupedExerciseEntryResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDateResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDayResponse;
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

	@PostMapping("/cardioWorkout")
	public ApiResponse addCardioWorkout(@RequestBody CardioEntryDto cardioEntryDto) {
		return workoutService.addCardioWorkout(cardioEntryDto);
	}

	@GetMapping("/allWorkout")
	public ResponseEntity<WorkoutDayResponse> getAlltWorkoutByDate(@RequestParam("date") String date) {
		WorkoutDayResponse workoutDay = workoutService.getAllWorkoutForDate(date);
		return ResponseEntity.ok(workoutDay);
	}

	@GetMapping("/workout-dates")
	public ResponseEntity<WorkoutDateResponse> getWorkoutDates() {
		WorkoutDateResponse response = workoutService.getWorkoutDates();
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/workout/updateExercise/{exerciseId}")
	public ResponseEntity<ApiResponse> updateExercise(@PathVariable Long exerciseId, @RequestBody ExerciseEntryDto exerciseEntryDto){
		ApiResponse response = workoutService.updateStrengthEntry(exerciseId,exerciseEntryDto);
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/workout/updateCardio/{exerciseId}")
	public ResponseEntity<ApiResponse> updateCardio(@PathVariable Long exerciseId, @RequestBody CardioEntryDto cardioEntryDto){
		ApiResponse response = workoutService.updateCardioEntry(exerciseId, cardioEntryDto);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/workout/deleteExercise/{exerciseId}")
	public ApiResponse deleteWorkout(@PathVariable Long exerciseId) {
		return workoutService.deleteWorkout(exerciseId);
	}
	
	public ResponseEntity<ApiResponse> deleteCardio(@PathVariable Long exerciseId){
		ApiResponse response = workoutService.deleteCardio(exerciseId);
		return ResponseEntity.ok(response);
	}
}
