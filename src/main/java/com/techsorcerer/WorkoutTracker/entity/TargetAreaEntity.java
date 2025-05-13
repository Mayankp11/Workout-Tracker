package com.techsorcerer.WorkoutTracker.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "target_area")
public class TargetAreaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String label;
	
	@OneToMany(mappedBy = "targetArea", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExerciseNameEntity> exercises;
	
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

	public List<ExerciseNameEntity> getExercises() {
		return exercises;
	}

	public void setExercises(List<ExerciseNameEntity> exercises) {
		this.exercises = exercises;
	}
	
}
