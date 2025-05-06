package com.techsorcerer.WorkoutTracker.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.ExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.ExerciseSetDto;
import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.ExerciseEntryEntity;
import com.techsorcerer.WorkoutTracker.entity.ExerciseSetEntity;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;
import com.techsorcerer.WorkoutTracker.repository.WorkoutSessionRepository;
import com.techsorcerer.WorkoutTracker.service.WorkoutService;

@Service
public class WorkoutServiceImpl implements WorkoutService {
	
	@Autowired
	private WorkoutSessionRepository sessionRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public WorkoutSessionEntity createWorkout(WorkoutSessionDto workoutSessionDto) {
		WorkoutSessionEntity session = new WorkoutSessionEntity();

		
		session.setDate(LocalDate.now()); 
		
		//list in the return value
		List<ExerciseEntryEntity> exerciseEnteries = new ArrayList<>();
		
		//the list which is coming from the request body
		for(ExerciseEntryDto dto : workoutSessionDto.getExercises()) {
			ExerciseEntryEntity entry = modelMapper.map(dto, ExerciseEntryEntity.class);
			entry.setSession(session);
			
			// Convert inner ExerciseSetDto list to ExerciseSet entities
			List<ExerciseSetEntity> exerciseSetEntities = new ArrayList<>();
			for (ExerciseSetDto setDto : dto.getSets()) {
				ExerciseSetEntity set = modelMapper.map(setDto, ExerciseSetEntity.class);
				set.setExerciseEntry(entry);//link back to ExerciseEntry
				exerciseSetEntities.add(set);
			}
			
			entry.setSets(exerciseSetEntities);
			exerciseEnteries.add(entry);
			
		}
		
		session.setExercises(exerciseEnteries);
		sessionRepository.save(session);
		
		return session;
	}

}
