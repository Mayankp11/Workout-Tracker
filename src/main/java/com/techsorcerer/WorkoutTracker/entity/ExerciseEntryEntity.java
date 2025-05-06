package com.techsorcerer.WorkoutTracker.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Exercise_Entry")
public class ExerciseEntryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String exerciseName;
	private String targetArea;
	
	@ManyToOne
	@JoinColumn(name = "session_id")
	
	private WorkoutSessionEntity session;
	@OneToMany(mappedBy = "exerciseEntry", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExerciseSetEntity> sets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public WorkoutSessionEntity getSession() {
		return session;
	}

	public void setSession(WorkoutSessionEntity session) {
		this.session = session;
	}

	public List<ExerciseSetEntity> getSets() {
		return sets;
	}

	public void setSets(List<ExerciseSetEntity> sets) {
		this.sets = sets;
	}

	public String getTargetArea() {
		return targetArea;
	}

	public void setTargetArea(String targetArea) {
		this.targetArea = targetArea;
	}
}
