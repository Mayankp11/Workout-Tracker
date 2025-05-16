package com.techsorcerer.WorkoutTracker.enums;

public enum ErrorMessages {
	EMAIL_ALREADY_EXISTS("Email already exists."), 
	USER_NOT_FOUND("No user found"), 
	EMAIL_AND_PASSWORD_DO_NOT_MATCH("Email and password do not match"),  
	INVALID_CREDENTIALS("Invalid credentials"),
	INVALID_DATE_FORMAT("Invalid date format. Use yyyy-MM-dd."), 
	WORKOUT_NOT_FOUND("No workout found"), 
	EXERCISE_NOT_FOUND("Exercise not found"), 
	UNAUTHORIZED_ACCESS("Not authorized to access the data"), 
	INVALID_DATE("Future dates not allowed"), 
	DATA_CANNOT_BE_NULL("Data cannot be null"), 
	EXERCISE_ALREADY_EXISTS("Exercise already exists");
	
	private final String message;
	
	ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
