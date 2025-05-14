package com.techsorcerer.WorkoutTracker.dto;

public class TargetAreaWithCount {
	private long totalCount;
	private String targetArea;
	
	public TargetAreaWithCount(long totalCount, String targetArea) {
		this.totalCount = totalCount;
		this.targetArea = targetArea;
	}
	
	// Getters and Setters
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public String getTargetArea() {
		return targetArea;
	}
	public void setTargetArea(String targetArea) {
		this.targetArea = targetArea;
	}


}
