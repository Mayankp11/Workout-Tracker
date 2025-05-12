package com.techsorcerer.WorkoutTracker.dto;

public class UserDetailsDto {
	
	private int age;
    private double height;        // as entered by user
    private String heightUnit;    // "cm" or "in"
    private double weight;
    private String weightUnit;    // "kg" or "lb"
    
    // Getters and Setters
    
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getHeightUnit() {
		return heightUnit;
	}
	public void setHeightUnit(String heightUnit) {
		this.heightUnit = heightUnit;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	
	// Getters and Setters
	

	
}
