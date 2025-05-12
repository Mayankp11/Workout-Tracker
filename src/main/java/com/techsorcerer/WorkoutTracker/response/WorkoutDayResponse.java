package com.techsorcerer.WorkoutTracker.response;

import java.time.LocalDate;
import java.util.List;

public class WorkoutDayResponse {
	private String userId;
	private LocalDate date;
	private List<CardioEntryResponse> cardioEntries;
	private List<GroupedExerciseEntryResponse> strengthEntries;
	
	// Getters and Setters
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<GroupedExerciseEntryResponse> getStrengthEntries() {
		return strengthEntries;
	}
	public void setStrengthEntries(List<GroupedExerciseEntryResponse> strengthEntries) {
		this.strengthEntries = strengthEntries;
	}
	public List<CardioEntryResponse> getCardioEntries() {
		return cardioEntries;
	}
	public void setCardioEntries(List<CardioEntryResponse> cardioEntries) {
		this.cardioEntries = cardioEntries;
	}

}
