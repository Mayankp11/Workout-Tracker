package com.techsorcerer.WorkoutTracker.enums;

public enum SuccessMessages {
	LOGIN_SUCCESSFUL("Login successfull"),
	INVALID_CREDENTIALS("Invalid credentials"),
	WORKOUT_SAVED("Successfully saved the workout"), 
	EXERCISE_UPDATED_SUCCESSFULLY("Successfully updated exercise data"),
	EXERCISE_DELETED_SUCCESSFULLY("Exercise deleted successfully"), 
	CARDIO_UPDATED_SUCCESSFULLY("Cardio data updated successfully"),
	DETAILS_UPDATED_SUCCESSFULLY("Details successfully updated"), 
	DATA_ADDED_SUCCESSFULLY("Data added successfully"),
	DATA_UPDATED_SUCCESSFULLY("Data updated successfully");
	
	private final String message;

    SuccessMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
