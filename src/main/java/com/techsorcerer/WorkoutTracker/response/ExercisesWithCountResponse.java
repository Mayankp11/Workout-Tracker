package com.techsorcerer.WorkoutTracker.response;

import java.util.List;

import com.techsorcerer.WorkoutTracker.dto.ExerciseMetaDataDto;

public class ExercisesWithCountResponse {
	private int totalCount;
	private List<ExerciseMetaDataDto> exercises;
	
	// Getters and Setters
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<ExerciseMetaDataDto> getExercises() {
		return exercises;
	}
	public void setExercises(List<ExerciseMetaDataDto> exercises) {
		this.exercises = exercises;
	}

}
