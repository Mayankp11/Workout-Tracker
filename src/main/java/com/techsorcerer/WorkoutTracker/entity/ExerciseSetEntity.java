package com.techsorcerer.WorkoutTracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Exercise_Sets")
public class ExerciseSetEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int setNumber;
	
	private double weight;
	
	private int reps;
	
	@ManyToOne
	@JoinColumn(name = "exercise_entry_id")
	private ExerciseEntryEntity exerciseEntryEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSetNumber() {
		return setNumber;
	}

	public void setSetNumber(int setNumber) {
		this.setNumber = setNumber;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public ExerciseEntryEntity getExerciseEntry() {
		return exerciseEntryEntity;
	}

	public void setExerciseEntry(ExerciseEntryEntity exerciseEntryEntity) {
		this.exerciseEntryEntity = exerciseEntryEntity;
	}

}
