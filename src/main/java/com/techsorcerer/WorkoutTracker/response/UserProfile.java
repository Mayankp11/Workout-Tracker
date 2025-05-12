package com.techsorcerer.WorkoutTracker.response;

public class UserProfile {
	
	private String userId;
    private String name;
    private int age;
    private String height; // e.g., "5'7"" or "170 cm"
    private int weight;    // e.g., 155
    private String weightUnit; // always "lb"
    
    // Getters and Setters
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
}
