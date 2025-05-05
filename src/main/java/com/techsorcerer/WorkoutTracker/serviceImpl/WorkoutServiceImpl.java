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
import com.techsorcerer.WorkoutTracker.entity.ExerciseEntry;
import com.techsorcerer.WorkoutTracker.entity.ExerciseSet;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSession;
import com.techsorcerer.WorkoutTracker.repository.WorkoutSessionRepository;
import com.techsorcerer.WorkoutTracker.service.WorkoutService;

@Service
public class WorkoutServiceImpl implements WorkoutService {
	
	@Autowired
	private WorkoutSessionRepository sessionRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public WorkoutSession createWorkout(WorkoutSessionDto workoutSessionDto) {
		WorkoutSession session = new WorkoutSession();

		session.setName(workoutSessionDto.getName());
		session.setDate(LocalDate.now()); 
		
		//list in the return value
		List<ExerciseEntry> exerciseEnteries = new ArrayList<>();
		
		//the list which is coming from the request body
		for(ExerciseEntryDto dto : workoutSessionDto.getExercises()) {
			ExerciseEntry entry = modelMapper.map(dto, ExerciseEntry.class);
			entry.setSession(session);
			
			// Convert inner ExerciseSetDto list to ExerciseSet entities
			List<ExerciseSet> exerciseSets = new ArrayList<>();
			for (ExerciseSetDto setDto : dto.getSets()) {
				ExerciseSet set = modelMapper.map(setDto, ExerciseSet.class);
				set.setExerciseEntry(entry);//link back to ExerciseEntry
				exerciseSets.add(set);
			}
			
			entry.setSets(exerciseSets);
			exerciseEnteries.add(entry);
			
		}
		
		session.setExercises(exerciseEnteries);
		sessionRepository.save(session);
		
		return session;
	}

}
