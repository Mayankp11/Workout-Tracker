package com.techsorcerer.WorkoutTracker.response;

import java.util.List;

public class GroupedExerciseEntryResponse {
	private String userId;
	private String exerciseName;
    private String targetArea;
    private List<Integer> sets;
    private List<Double> weights;
    private List<Integer> reps;
    
    
    // Getters adn Setters
    
    
	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	public String getTargetArea() {
		return targetArea;
	}
	public void setTargetArea(String targetArea) {
		this.targetArea = targetArea;
	}
	public List<Integer> getSets() {
		return sets;
	}
	public void setSets(List<Integer> sets) {
		this.sets = sets;
	}
	public List<Double> getWeights() {
		return weights;
	}
	public void setWeights(List<Double> weights) {
		this.weights = weights;
	}
	public List<Integer> getReps() {
		return reps;
	}
	public void setReps(List<Integer> reps) {
		this.reps = reps;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
