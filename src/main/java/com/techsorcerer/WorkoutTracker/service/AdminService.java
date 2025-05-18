package com.techsorcerer.WorkoutTracker.service;

import com.techsorcerer.WorkoutTracker.response.UserResponse;

public interface AdminService {

	UserResponse getUserDetails(String identifier);

}
