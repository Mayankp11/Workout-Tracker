package com.techsorcerer.WorkoutTracker.dto;

public class TargetAreaDto {
	private Long id;
	private String label;

	public TargetAreaDto(Long id, String label) {
		this.id = id;
		this.label = label;
	}

	// Getters and setters
	
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

}
