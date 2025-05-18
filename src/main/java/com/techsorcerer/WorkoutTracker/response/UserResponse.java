package com.techsorcerer.WorkoutTracker.response;

import java.time.LocalDateTime;

public class UserResponse {
	 private String userId;
	    private String name;
	    private String email; 
	    private LocalDateTime createdAt;
	    
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
}
