package com.techsorcerer.WorkoutTracker.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techsorcerer.WorkoutTracker.dto.LoginDto;
import com.techsorcerer.WorkoutTracker.dto.UserDto;
import com.techsorcerer.WorkoutTracker.dto.UserDetailsDto;
import com.techsorcerer.WorkoutTracker.entity.ProfileEntity;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.enums.ErrorMessages;
import com.techsorcerer.WorkoutTracker.enums.Roles;
import com.techsorcerer.WorkoutTracker.enums.SuccessMessages;
import com.techsorcerer.WorkoutTracker.exceptions.AccessDeniedException;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;

import com.techsorcerer.WorkoutTracker.repository.UserProfileRepository;
import com.techsorcerer.WorkoutTracker.repository.UserRepository;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.UserProfile;
import com.techsorcerer.WorkoutTracker.response.UserResponse;
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
	public UserResponse createUser(UserDto userDto) {
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

		userRepository.findByEmail(userEntity.getEmail())
				.orElseThrow(() -> new UserServiceExceptions(ErrorMessages.EMAIL_ALREADY_EXISTS.getMessage()));

		String userId = StringGenerator.userIdGenerator(userDto.getName());
		userEntity.setUserId(userId);

		userEntity.setRole(Roles.USER.name());

		userRepository.save(userEntity);

		UserResponse responseDto = modelMapper.map(userEntity, UserResponse.class);
		return responseDto;
	}

	@Override
	public boolean authenticateUser(@Valid LoginDto loginDto) {
		UserEntity user = userRepository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

		if (!user.getPassword().equals(loginDto.getPassword())) {
			throw new UserServiceExceptions(ErrorMessages.EMAIL_AND_PASSWORD_DO_NOT_MATCH.getMessage());
		}
		return true;
	}

	@Override
	public String loginAndGenerateToken(LoginDto loginDto) {
		UserEntity user = userRepository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

		if (user == null || !user.getPassword().equals(loginDto.getPassword())) {
			throw new UserServiceExceptions(ErrorMessages.INVALID_CREDENTIALS.getMessage());
		}

		return jwtUtil.generateToken(user.getUserId(), user.getEmail(), user.getRole());
	}

	@Override
	public ApiResponse addUserDetails(UserDetailsDto details) {
		ProfileEntity entity = new ProfileEntity();

		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

		entity.setUser(user);
		entity.setAge(details.getAge());

		// ✅ Convert and store height in inches
		double heightInInches = "cm".equalsIgnoreCase(details.getHeightUnit()) ? details.getHeight() * 0.393701
				: details.getHeight();
		entity.setHeight(heightInInches);

		// ✅ Convert and store weight in lb
		double weightInLb = "kg".equalsIgnoreCase(details.getWeightUnit()) ? details.getWeight() * 2.20462
				: details.getWeight();
		entity.setWeight(weightInLb);

		profileRepository.save(entity);

		UserDetailsDto response = new UserDetailsDto();
		response.setAge(entity.getAge());
		response.setHeight(entity.getHeight());
		response.setWeight(entity.getWeight());

		return new ApiResponse("success", SuccessMessages.DETAILS_UPDATED_SUCCESSFULLY.getMessage());
	}

	@Override
	public UserProfile getUserProfile(String userId) {
		ProfileEntity profile = profileRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

		UserEntity user = profile.getUser();
		UserProfile response = new UserProfile();
		response.setUserId(user.getUserId());
		response.setName(user.getName());
		response.setAge(profile.getAge());

		// Convert height from inches to feet and inches (e.g., "5'7")
		int totalInches = (int) Math.round(profile.getHeight());
		int feet = totalInches / 12;
		int inches = totalInches % 12;
		response.setHeight(feet + "'" + inches);

		// Set weight in lb
		response.setWeight((int) Math.round(profile.getWeight()));
		response.setWeightUnit("lb");
		response.setLastUpdated(profile.getLastUpdated());

		return response;
	}

	@Override
	@Transactional
	public ApiResponse updateUserDetails(UserDetailsDto dto) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));
		if (!user.getUserId().equals(userId)) {
			throw new AccessDeniedException(ErrorMessages.UNAUTHORIZED_ACCESS.getMessage());
		}

		ProfileEntity profile = profileRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

// 3. Map and convert units
		profile.setAge(dto.getAge());

// height: always store in inches
		if ("cm".equalsIgnoreCase(dto.getHeightUnit())) {
			profile.setHeight(dto.getHeight() / 2.54);
		} else {
			profile.setHeight(dto.getHeight());
		}

// weight: always store in pounds
		if ("kg".equalsIgnoreCase(dto.getWeightUnit())) {
			profile.setWeight(dto.getWeight() * 2.20462);
		} else {
			profile.setWeight(dto.getWeight());
		}

		profileRepository.save(profile);

		return new ApiResponse("success", SuccessMessages.DETAILS_UPDATED_SUCCESSFULLY.getMessage());
	}

}
