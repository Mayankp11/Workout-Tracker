package com.techsorcerer.WorkoutTracker.dto;

import java.util.List;

public class ExercisesWithCountDto {
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
