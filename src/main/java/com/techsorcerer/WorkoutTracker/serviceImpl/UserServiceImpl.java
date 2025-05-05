package com.techsorcerer.WorkoutTracker.serviceImpl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.LoginDto;
import com.techsorcerer.WorkoutTracker.dto.UserDto;
import com.techsorcerer.WorkoutTracker.dto.UserResponseDto;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.UserRepository;
import com.techsorcerer.WorkoutTracker.response.ErrorMessages;
import com.techsorcerer.WorkoutTracker.service.UserService;
import com.techsorcerer.WorkoutTracker.util.StringGenerator;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserResponseDto createUser(UserDto userDto) {
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		UserEntity checkEmail = userRepository.findByEmail(userEntity.getEmail());
		if(checkEmail != null) {
			throw new UserServiceExceptions(ErrorMessages.EMAIL_ALREADY_EXISTS.getMessage());
		}
		
		String userId = StringGenerator.userIdGenerator(userDto.getName());
		userEntity.setUserId(userId);
		
		userEntity.setCreatedAt(LocalDateTime.now());
		
		userRepository.save(userEntity);
		
		UserResponseDto responseDto = modelMapper.map(userEntity, UserResponseDto.class);
		return responseDto;
	}

	@Override
	public boolean authenticateUser(@Valid LoginDto loginDto) {
		UserEntity user = userRepository.findByEmail(loginDto.getEmail());
		if(user == null) {
			throw new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage());
		}
		
		if(!user.getPassword().equals(loginDto.getPassword())){
			throw new UserServiceExceptions(ErrorMessages.EMAIL_AND_PASSWORD_DO_NOT_MATCH.getMessage());
		}
		return true;
	}

}
