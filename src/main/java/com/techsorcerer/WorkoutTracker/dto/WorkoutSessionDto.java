package com.techsorcerer.WorkoutTracker.dto;

import java.time.LocalDate;
import java.util.List;

import com.techsorcerer.WorkoutTracker.entity.UserEntity;

public class WorkoutSessionDto {
	
	private String name;
	private LocalDate date;
	private List<ExerciseEntryDto> exercises;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<ExerciseEntryDto> getExercises() {
		return exercises;
	}
	public void setExercises(List<ExerciseEntryDto> exercises) {
		this.exercises = exercises;
	}
	
	
	
	
	

}
