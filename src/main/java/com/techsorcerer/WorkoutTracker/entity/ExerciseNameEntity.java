package com.techsorcerer.WorkoutTracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise_name")
public class ExerciseNameEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String label;
	
	@ManyToOne
	@JoinColumn(name = "target_area_id", nullable = false)
	private TargetAreaEntity targetArea;
	
	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public TargetAreaEntity getTargetArea() {
		return targetArea;
	}

	public void setTargetArea(TargetAreaEntity targetArea) {
		this.targetArea = targetArea;
	}
}
