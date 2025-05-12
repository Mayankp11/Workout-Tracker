package com.techsorcerer.WorkoutTracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cardio_Entry")
public class CardioEntryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cardioType;
	private double speed;
	private int level;
	private int durationMinutes;
	private double miles;
	
	@ManyToOne
	@JoinColumn(name = "session_id")
	private WorkoutSessionEntity session;
	
	//Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public WorkoutSessionEntity getSession() {
		return session;
	}

	public void setSession(WorkoutSessionEntity session) {
		this.session = session;
	}
}
