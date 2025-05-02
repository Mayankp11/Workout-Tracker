package com.techsorcerer.WorkoutTracker.dto;

import java.time.LocalDate;
import java.util.List;

public class WorkoutSessionDto {
	
	private String name;
	private String date;
	private List<ExerciseEntryDto> exercises;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<ExerciseEntryDto> getExercises() {
		return exercises;
	}
	public void setExercises(List<ExerciseEntryDto> exercises) {
		this.exercises = exercises;
	}
	
	
	

}
