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

@Entity
public class ExerciseEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String exerciseName;
	
	@ManyToOne
	@JoinColumn(name = "session_id")
	private WorkoutSession session;
	
	@OneToMany(mappedBy = "exerciseEntry", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExerciseSet> sets;

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

	public WorkoutSession getSession() {
		return session;
	}

	public void setSession(WorkoutSession session) {
		this.session = session;
	}

	public List<ExerciseSet> getSets() {
		return sets;
	}

	public void setSets(List<ExerciseSet> sets) {
		this.sets = sets;
	}
}
