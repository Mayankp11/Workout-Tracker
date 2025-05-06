package com.techsorcerer.WorkoutTracker.response;

import java.util.List;

public class WorkoutDatesResponse {
	private String userId;
    private List<String> workoutDates;

    public WorkoutDatesResponse(String userId, List<String> workoutDates) {
        this.userId = userId;
        this.workoutDates = workoutDates;
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
