package com.techsorcerer.WorkoutTracker.response;

public enum ErrorMessages {
	EMAIL_ALREADY_EXISTS("Email already exists."), 
	USER_NOT_FOUND("No user found"), 
	EMAIL_AND_PASSWORD_DO_NOT_MATCH("Email and password do not match"),  
	INVALID_CREDENTIALS("Invalid credentials");
	
	private final String message;
	
	ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
