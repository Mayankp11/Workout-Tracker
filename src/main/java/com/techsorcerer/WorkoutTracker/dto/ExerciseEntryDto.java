package com.techsorcerer.WorkoutTracker.dto;

import java.util.List;

public class ExerciseEntryDto {
	
	private String exerciseName;
	private List<ExerciseSetDto> sets;
	
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
