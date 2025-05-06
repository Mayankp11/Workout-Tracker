package com.techsorcerer.WorkoutTracker.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.ExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.ExerciseSetDto;
import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.ExerciseEntryEntity;
import com.techsorcerer.WorkoutTracker.entity.ExerciseSetEntity;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.UserRepository;
import com.techsorcerer.WorkoutTracker.repository.WorkoutSessionRepository;
import com.techsorcerer.WorkoutTracker.response.ErrorMessages;
import com.techsorcerer.WorkoutTracker.security.JwtUtil;
import com.techsorcerer.WorkoutTracker.service.WorkoutService;

@Service
public class WorkoutServiceImpl implements WorkoutService {
	
	@Autowired
	private WorkoutSessionRepository sessionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public WorkoutSessionEntity createWorkout(WorkoutSessionDto workoutSessionDto) {
	    WorkoutSessionEntity session = new WorkoutSessionEntity();
	    session.setDate(LocalDate.now()); 

	    // 1. Get userId from JWT context
	    String userId = SecurityContextHolder.getContext().getAuthentication().getName();

	    // 2. Fetch user
	    UserEntity user = userRepository.findById(userId)
	        .orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

	    // ✅ Link session to user
	    session.setUser(user);

	    // 3. Map exercises and sets
	    List<ExerciseEntryEntity> exerciseEntries = new ArrayList<>();
	    for (ExerciseEntryDto dto : workoutSessionDto.getExercises()) {
	        ExerciseEntryEntity entry = modelMapper.map(dto, ExerciseEntryEntity.class);
	        entry.setSession(session);

	        List<ExerciseSetEntity> exerciseSetEntities = new ArrayList<>();
	        for (ExerciseSetDto setDto : dto.getSets()) {
	            ExerciseSetEntity set = modelMapper.map(setDto, ExerciseSetEntity.class);
	            set.setExerciseEntry(entry);
	            exerciseSetEntities.add(set);
	        }

	        entry.setSets(exerciseSetEntities);
	        exerciseEntries.add(entry);
	    }

	    session.setExercises(exerciseEntries);
	    sessionRepository.save(session);

	    return session;
	}


}
