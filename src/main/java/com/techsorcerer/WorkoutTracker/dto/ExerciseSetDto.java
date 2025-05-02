package com.techsorcerer.WorkoutTracker.dto;

public class ExerciseSetDto {
	
	private int setNumber;
	private double weight;
	private int reps;
	
	public int getSetNumber() {
		return setNumber;
	}
	public void setSetNumber(int setNumber) {
		this.setNumber = setNumber;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}

}
