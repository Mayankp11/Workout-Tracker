package com.techsorcerer.WorkoutTracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.GroupedExerciseEntryResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDatesResponse;


public interface WorkoutService {

	ApiResponse createWorkout(WorkoutSessionDto workoutSessionDto);

	List<GroupedExerciseEntryResponse> getWorkoutForDate(String dateStr);

	WorkoutDatesResponse getWorkoutDates();

}
