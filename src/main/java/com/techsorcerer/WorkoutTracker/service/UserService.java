package com.techsorcerer.WorkoutTracker.service;

import com.techsorcerer.WorkoutTracker.dto.LoginDto;
import com.techsorcerer.WorkoutTracker.dto.UserDto;
import com.techsorcerer.WorkoutTracker.dto.UserProfileDto;
import com.techsorcerer.WorkoutTracker.dto.UserResponseDto;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;

import jakarta.validation.Valid;

public interface UserService {

	UserResponseDto createUser(UserDto userDto);

	boolean authenticateUser(@Valid LoginDto loginDto);

	String loginAndGenerateToken(LoginDto loginDto);

	ApiResponse addUserDetails(UserProfileDto profileDto);

}
