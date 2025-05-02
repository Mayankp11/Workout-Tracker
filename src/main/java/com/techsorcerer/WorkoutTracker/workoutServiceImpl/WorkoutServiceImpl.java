package com.techsorcerer.WorkoutTracker.workoutServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSession;
import com.techsorcerer.WorkoutTracker.repository.WorkoutSessionRepository;
import com.techsorcerer.WorkoutTracker.workoutService.WorkoutService;

@Service
public class WorkoutServiceImpl implements WorkoutService {
	
	@Autowired
	private WorkoutSessionRepository sessionRepository;

	@Override
	public WorkoutSession createWorkout(WorkoutSessionDto workoutSessionDto) {
		WorkoutSession returnValue = new WorkoutSession();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(formatter);
		
		returnValue.setName(workoutSessionDto.getName());
		returnValue.setDate(formattedDate);
		return null;
	}

}
