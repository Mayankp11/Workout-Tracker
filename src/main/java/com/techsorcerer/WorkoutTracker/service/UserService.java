package com.techsorcerer.WorkoutTracker.service;

import com.techsorcerer.WorkoutTracker.dto.UserDto;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;

public interface UserService {

	UserEntity createUser(UserDto userDto);

}
