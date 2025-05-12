package com.techsorcerer.WorkoutTracker.service;

import java.util.List;

import com.techsorcerer.WorkoutTracker.dto.CardioEntryDto;
import com.techsorcerer.WorkoutTracker.dto.ExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.UpdateExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;

import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.GroupedExerciseEntryResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDateResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDayResponse;

public interface WorkoutService {

	ApiResponse createWorkout(WorkoutSessionDto workoutSessionDto);

	WorkoutDateResponse getWorkoutDates();

	ApiResponse deleteWorkout(Long exerciseId);

	ApiResponse addCardioWorkout(CardioEntryDto cardioEntryDto);

	WorkoutDayResponse getAllWorkoutForDate(String date);

	ApiResponse updateStrengthEntry(Long exerciseId, ExerciseEntryDto exerciseEntryDto);

	ApiResponse updateCardioEntry(Long exerciseId, CardioEntryDto cardioEntryDto);

	ApiResponse deleteCardio(Long exerciseId);

}
