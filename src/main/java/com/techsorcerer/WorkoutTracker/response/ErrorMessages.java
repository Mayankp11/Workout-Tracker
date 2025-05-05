package com.techsorcerer.WorkoutTracker.response;

public enum ErrorMessages {
	EMAIL_ALREADY_EXISTS("Email already exists");
	
	private final String message;
	
	ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
