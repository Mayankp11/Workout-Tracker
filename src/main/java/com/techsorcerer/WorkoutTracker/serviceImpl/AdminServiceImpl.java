package com.techsorcerer.WorkoutTracker.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.enums.ErrorMessages;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.UserRepository;
import com.techsorcerer.WorkoutTracker.response.UserResponse;
import com.techsorcerer.WorkoutTracker.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserResponse getUserDetails(String identifier) {
	    UserEntity user = userRepository.findById(identifier).orElse(null);

	    if (user == null) {
	        user = userRepository.findByNameIgnoreCase(identifier).orElse(null);
	    }

	    if (user == null) {
	        user = userRepository.findByEmail(identifier).orElse(null);
	    }

	    if (user == null) {
	        throw new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage());
	    }

	    return modelMapper.map(user, UserResponse.class);
	}


}
