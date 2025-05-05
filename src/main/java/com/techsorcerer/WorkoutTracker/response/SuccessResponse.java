package com.techsorcerer.WorkoutTracker.response;

public enum SuccessResponse {
	LOGIN_SUCCESSFUL("Login successfull"),
	INVALID_CREDENTIALS("Invalid credentials");
	
	private final String message;

    SuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
