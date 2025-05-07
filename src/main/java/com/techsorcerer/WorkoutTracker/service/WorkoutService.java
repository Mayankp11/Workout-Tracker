package com.techsorcerer.WorkoutTracker.service;

import java.util.List;

import com.techsorcerer.WorkoutTracker.dto.UpdateExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;

import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.GroupedExerciseEntryResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDatesResponse;

public interface WorkoutService {

	ApiResponse createWorkout(WorkoutSessionDto workoutSessionDto);

	List<GroupedExerciseEntryResponse> getWorkoutForDate(String dateStr);

	WorkoutDatesResponse getWorkoutDates();

	ApiResponse updateExerciseEntry(Long exerciseId, UpdateExerciseEntryDto exerciseEntryDto);

}
