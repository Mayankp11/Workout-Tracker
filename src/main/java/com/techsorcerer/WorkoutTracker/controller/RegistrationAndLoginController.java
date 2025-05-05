package com.techsorcerer.WorkoutTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.UserDto;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.service.UserService;

@RestController
@RequestMapping("/api/user")
public class RegistrationAndLoginController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public UserEntity registerUser(@RequestBody UserDto userDto) {
		UserEntity returnValue = userService.createUser(userDto);
		return returnValue;
	}
}
