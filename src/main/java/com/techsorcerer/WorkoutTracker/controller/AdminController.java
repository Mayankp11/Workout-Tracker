package com.techsorcerer.WorkoutTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.response.UserResponse;
import com.techsorcerer.WorkoutTracker.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/user/{identifier}")
	public ResponseEntity<UserResponse> getUserDetails(@PathVariable String identifier){
		UserResponse user = adminService.getUserDetails(identifier);
		return ResponseEntity.ok(user);
	}
}
