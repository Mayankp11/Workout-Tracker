package com.techsorcerer.WorkoutTracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginDto {
	
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    public String getEmail() {
		return email;
	}
    
    //Getters and Setters

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank(message = "Password is required")
    private String password;

}
