package com.techsorcerer.WorkoutTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.UserDetailsDto;

import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.UserProfile;
import com.techsorcerer.WorkoutTracker.service.UserService;

@RestController
@RequestMapping("/user/profile")
public class UserProfileController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/addDetails")
	public ResponseEntity<ApiResponse> userDetails(@RequestBody UserDetailsDto details) {
		ApiResponse user = userService.addUserDetails(details);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<UserProfile> getUserProfile(){
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		UserProfile user = userService.getUserProfile(userId);
		return ResponseEntity.ok(user);
	}
	
	 @PutMapping("/updateDetails")
	    public ResponseEntity<ApiResponse> updateUserDetails(@RequestBody UserDetailsDto details) {
	        ApiResponse response = userService.updateUserDetails(details);
	        return ResponseEntity.ok(response);
	    }
	

}
