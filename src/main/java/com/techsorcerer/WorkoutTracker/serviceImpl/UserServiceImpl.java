package com.techsorcerer.WorkoutTracker.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.UserDto;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.UserRepository;
import com.techsorcerer.WorkoutTracker.response.ErrorMessages;
import com.techsorcerer.WorkoutTracker.service.UserService;
import com.techsorcerer.WorkoutTracker.util.StringGenerator;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserEntity createUser(UserDto userDto) {
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		UserEntity checkEmail = userRepository.findByEmail(userEntity.getEmail());
		if(checkEmail != null) {
			throw new UserServiceExceptions(ErrorMessages.EMAIL_ALREADY_EXISTS.getMessage());
		}
		
		String userId = StringGenerator.userIdGenerator(userDto.getName());
		userEntity.setUserId(userId);
		
		userRepository.save(userEntity);
		
		return userEntity;
	}

}
