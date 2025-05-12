package com.techsorcerer.WorkoutTracker.response;

public enum SuccessResponse {
	LOGIN_SUCCESSFUL("Login successfull"),
	INVALID_CREDENTIALS("Invalid credentials"),
	WORKOUT_SAVED("Successfully saved the workout"), 
	EXERCISE_UPDATED_SUCCESSFULLY("Successfully updated exercise data"),
	EXERCISE_DELETED_SUCCESSFULLY("Exercise deleted successfully"), 
	CARDIO_UPDATED_SUCCESSFULLY("Cardio data updated successfully");
	
	private final String message;

    SuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
