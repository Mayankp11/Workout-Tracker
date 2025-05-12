package com.techsorcerer.WorkoutTracker.dto;

import java.time.LocalDate;

public class UpdateCardioEntryDto {

	private String cardioType;
	private LocalDate date;
	private double speed;
	private int level;
	private int durationMinutes;
	private double miles;
	
	//Getters and Setters
	
	public String getCardioType() {
		return cardioType;
	}
	public void setCardioType(String cardioType) {
		this.cardioType = cardioType;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
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
