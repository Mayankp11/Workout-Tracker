package com.techsorcerer.WorkoutTracker.serviceImpl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.LoginDto;
import com.techsorcerer.WorkoutTracker.dto.UserDto;
import com.techsorcerer.WorkoutTracker.dto.UserProfileDto;
import com.techsorcerer.WorkoutTracker.dto.UserResponseDto;
import com.techsorcerer.WorkoutTracker.entity.ProfileEntity;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;
import com.techsorcerer.WorkoutTracker.exceptions.WorkoutServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.UserProfileRepository;
import com.techsorcerer.WorkoutTracker.repository.UserRepository;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.ErrorMessages;
import com.techsorcerer.WorkoutTracker.response.SuccessResponse;
import com.techsorcerer.WorkoutTracker.security.JwtUtil;
import com.techsorcerer.WorkoutTracker.service.UserService;
import com.techsorcerer.WorkoutTracker.util.StringGenerator;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserProfileRepository profileRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public UserResponseDto createUser(UserDto userDto) {
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		UserEntity checkEmail = userRepository.findByEmail(userEntity.getEmail());
		if(checkEmail != null) {
			throw new UserServiceExceptions(ErrorMessages.EMAIL_ALREADY_EXISTS.getMessage());
		}
		
		String userId = StringGenerator.userIdGenerator(userDto.getName());
		userEntity.setUserId(userId);
		
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
	
	

	@Override
	public String loginAndGenerateToken(LoginDto loginDto) {
	    UserEntity user = userRepository.findByEmail(loginDto.getEmail());

	    if (user == null || !user.getPassword().equals(loginDto.getPassword())) {
	        throw new UserServiceExceptions(ErrorMessages.INVALID_CREDENTIALS.getMessage());
	    }

	    return jwtUtil.generateToken(user.getUserId(), user.getEmail());
	}

	@Override
	public ApiResponse addUserDetails(UserProfileDto profileDto) {
		ProfileEntity entity = new ProfileEntity();
		
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));
		
		entity.setUser(user);
		entity.setAge(profileDto.getAge());
		entity.setHeight(profileDto.getHeight());
		entity.setWeight(profileDto.getWeight());
		
		profileRepository.save(entity);
		
		UserProfileDto response = new UserProfileDto();
		response.setUserId(userId);
		response.setAge(entity.getAge());
		response.setHeight(entity.getHeight());
		response.setWeight(entity.getWeight());
		
		return new ApiResponse("success",SuccessResponse.DETAILS_UPDATED_SUCCESSFULLY.getMessage());
	}

	


}
