package com.techsorcerer.WorkoutTracker.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_users")
public class UserEntity {
	
	@Id
	@Column(unique = true, nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<WorkoutSessionEntity> workoutSessionEntities;
	
	@Column( nullable = false,updatable = false)
	private LocalDateTime createdAt;
	
	@PrePersist
	protected void onCreate() {
	    this.createdAt = LocalDateTime.now();
	}
	
	
	// Getters and Setters
	
	public List<WorkoutSessionEntity> getWorkoutSessions() {
		return workoutSessionEntities;
	}
	public void setWorkoutSessions(List<WorkoutSessionEntity> workoutSessionEntities) {
		this.workoutSessionEntities = workoutSessionEntities;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
