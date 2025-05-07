package com.techsorcerer.WorkoutTracker.response;

public enum ErrorMessages {
	EMAIL_ALREADY_EXISTS("Email already exists."), 
	USER_NOT_FOUND("No user found"), 
	EMAIL_AND_PASSWORD_DO_NOT_MATCH("Email and password do not match"),  
	INVALID_CREDENTIALS("Invalid credentials"),
	INVALID_DATE_FORMAT("Invalid date format. Use yyyy-MM-dd."), 
	WORKOUT_NOT_FOUND("No workout found"), 
	EXERCISE_NOT_FOUND("Exercise not found"), 
	UNAUTHORIZED_ACCESS("Not authorized to access the data"), 
	INVALID_DATE("Future dates not allowed");
	
	private final String message;
	
	ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
