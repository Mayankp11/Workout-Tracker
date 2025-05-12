package com.techsorcerer.WorkoutTracker.service;

import com.techsorcerer.WorkoutTracker.dto.LoginDto;
import com.techsorcerer.WorkoutTracker.dto.UserDto;
import com.techsorcerer.WorkoutTracker.dto.UserDetailsDto;
import com.techsorcerer.WorkoutTracker.dto.UserResponseDto;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.UserProfile;

import jakarta.validation.Valid;

public interface UserService {

	UserResponseDto createUser(UserDto userDto);

	boolean authenticateUser(@Valid LoginDto loginDto);

	String loginAndGenerateToken(LoginDto loginDto);

	ApiResponse addUserDetails(UserDetailsDto details);

	UserProfile getUserProfile(String userId);

}
