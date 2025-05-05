package com.techsorcerer.WorkoutTracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDto {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Password cannot be empty")
	@Pattern(
		    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
		    message = "Password must be at least 6 characters and include 1 uppercase, 1 lowercase, 1 number, and 1 symbol"
		)
	private String password;
	
	// getters and setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
