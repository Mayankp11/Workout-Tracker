package com.techsorcerer.WorkoutTracker.dto;

import java.util.List;

public class ExerciseEntryDto {
	
	private String exerciseName;
	private String targetArea;
	private List<ExerciseSetDto> sets;
	
	//Getters and Setters
	
	public String getTargetArea() {
		return targetArea;
	}
	public void setTargetArea(String targetArea) {
		this.targetArea = targetArea;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	public List<ExerciseSetDto> getSets() {
		return sets;
	}
	public void setSets(List<ExerciseSetDto> sets) {
		this.sets = sets;
	}

}
