package com.techsorcerer.WorkoutTracker.response;

public class CardioEntryResponse {
	private Long cardioEntryId;
	private String cardioType;
	private double speed;
	private int level;
	private int durationMinutes;
	private double miles;
	
	// Getters and Setters
	
	public Long getCardioEntryId() {
		return cardioEntryId;
	}
	public void setCardioEntryId(Long cardioEntryId) {
		this.cardioEntryId = cardioEntryId;
	}
	public String getCardioType() {
		return cardioType;
	}
	public void setCardioType(String cardioType) {
		this.cardioType = cardioType;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	public double getMiles() {
		return miles;
	}
	public void setMiles(double miles) {
		this.miles = miles;
	}
}
