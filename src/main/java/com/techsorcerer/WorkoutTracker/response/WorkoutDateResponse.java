package com.techsorcerer.WorkoutTracker.response;

import java.util.List;

public class WorkoutDateResponse {
	private String userId;
    private List<String> workoutDates;

    public WorkoutDateResponse(String userId, List<String> workoutDates) {
        this.userId = userId;
        this.workoutDates = workoutDates;
    }

    
    public WorkoutDateResponse() {
    	// TODO Auto-generated constructor stub
    }

    // Getters and setters

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getWorkoutDates() {
		return workoutDates;
	}
	public void setWorkoutDates(List<String> workoutDates) {
		this.workoutDates = workoutDates;
	}
    
}
