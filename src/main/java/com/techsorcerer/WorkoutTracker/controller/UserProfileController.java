package com.techsorcerer.WorkoutTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.UserProfileDto;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.service.UserService;

@RestController
@RequestMapping("user/profile")
public class UserProfileController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/addDetails")
	public ResponseEntity<ApiResponse> userDetails(@RequestBody UserProfileDto profileDto) {
		ApiResponse user = userService.addUserDetails(profileDto);
		return ResponseEntity.ok(user);
	}
	
	pu

}
