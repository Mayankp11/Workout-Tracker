package com.techsorcerer.WorkoutTracker.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.LoginDto;
import com.techsorcerer.WorkoutTracker.dto.UserDto;
import com.techsorcerer.WorkoutTracker.enums.SuccessMessages;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;
import com.techsorcerer.WorkoutTracker.response.UserResponse;
import com.techsorcerer.WorkoutTracker.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class RegistrationAndLoginController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserDto userDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorMessages = bindingResult.getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage())
					.collect(Collectors.joining(", "));

			throw new UserServiceExceptions(errorMessages);
		}

		UserResponse response = userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorMessages = bindingResult.getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage())
					.collect(Collectors.joining(", "));
			throw new UserServiceExceptions(errorMessages);
		}

		boolean isAuthenticated = userService.authenticateUser(loginDto);
		if (isAuthenticated) {
			 String token = userService.loginAndGenerateToken(loginDto);
			    return ResponseEntity.ok("token: "+token);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(SuccessMessages.INVALID_CREDENTIALS.getMessage());
		}
	}
}
