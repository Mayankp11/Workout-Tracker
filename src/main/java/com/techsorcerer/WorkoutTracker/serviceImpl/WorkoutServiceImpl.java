package com.techsorcerer.WorkoutTracker.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techsorcerer.WorkoutTracker.dto.ExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.ExerciseSetDto;
import com.techsorcerer.WorkoutTracker.dto.UpdateExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.ExerciseEntryEntity;
import com.techsorcerer.WorkoutTracker.entity.ExerciseSetEntity;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.ExerciseEntryRepository;
import com.techsorcerer.WorkoutTracker.repository.UserRepository;
import com.techsorcerer.WorkoutTracker.repository.WorkoutSessionRepository;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.ErrorMessages;
import com.techsorcerer.WorkoutTracker.response.GroupedExerciseEntryResponse;
import com.techsorcerer.WorkoutTracker.response.SuccessResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDatesResponse;
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
    private ExerciseEntryRepository exerciseEntryRepository;

    @Override
    @Transactional
    public ApiResponse createWorkout(WorkoutSessionDto workoutSessionDto) {
    	//userId fetched
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

        LocalDate today = LocalDate.now();

        WorkoutSessionEntity session = sessionRepository.findByUserAndDate(user, today).orElse(null);

        if (session == null) {
            session = new WorkoutSessionEntity();
            session.setUser(user);
            session.setDate(today);
            session.setExercises(new ArrayList<>());
            
            session = sessionRepository.save(session);
        }

        List<ExerciseEntryEntity> newEntries = new ArrayList<>();

        for (ExerciseEntryDto dto : workoutSessionDto.getExercises()) {
            ExerciseEntryEntity existingEntry = exerciseEntryRepository
                    .findBySessionAndExerciseName(session, dto.getExerciseName())
                    .orElse(null);

            if (existingEntry != null) {
                for (ExerciseSetDto setDto : dto.getSets()) {
                    ExerciseSetEntity set = modelMapper.map(setDto, ExerciseSetEntity.class);
                    set.setExerciseEntry(existingEntry);
                    existingEntry.getSets().add(set);
                }
            } else {
                ExerciseEntryEntity entry = modelMapper.map(dto, ExerciseEntryEntity.class);
                entry.setSession(session);

                List<ExerciseSetEntity> setEntities = new ArrayList<>();
                for (ExerciseSetDto setDto : dto.getSets()) {
                    ExerciseSetEntity set = modelMapper.map(setDto, ExerciseSetEntity.class);
                    set.setExerciseEntry(entry);
                    setEntities.add(set);
                }

                entry.setSets(setEntities);
                newEntries.add(entry);
            }
        }

        session.getExercises().addAll(newEntries);
        sessionRepository.save(session);

        return new ApiResponse("success", SuccessResponse.WORKOUT_SAVED.getMessage());
    }


	@Override
	public List<GroupedExerciseEntryResponse> getWorkoutForDate(String dateStr) {
		
		//fetch userId
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		  UserEntity user = userRepository.findById(userId)
	                .orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));
		  
		  LocalDate date = LocalDate.parse(dateStr); // Format: yyyy-MM-dd
		  
		  // find the workout for that specific date
		  WorkoutSessionEntity sessionEntity = sessionRepository.findByUserAndDate(user, date)
				  .orElseThrow(() -> new UserServiceExceptions(ErrorMessages.WORKOUT_NOT_FOUND.getMessage()));
		  
		  List<GroupedExerciseEntryResponse> responseList = new ArrayList<>();
		  
		  for(ExerciseEntryEntity entry: sessionEntity.getExercises()) {
			  GroupedExerciseEntryResponse response = new GroupedExerciseEntryResponse();
			  response.setUserId(userId);
			  response.setExerciseId(entry.getId());
			  response.setExerciseName(entry.getExerciseName());
			  response.setTargetArea(entry.getTargetArea());
			  
			  
			  List<Integer> sets = new ArrayList<>();
			  List<Double> weights = new ArrayList<>();
			  List<Integer> reps = new ArrayList<>();
			  
			  for(ExerciseSetEntity set : entry.getSets()) {
				  sets.add(set.getSetNumber());
				  weights.add(set.getWeight());
				  reps.add(set.getReps());
			  }
			  
			  response.setSets(sets);
			  response.setWeights(weights);
			  response.setReps(reps);
			  
			  // add the resonse to return value
			  responseList.add(response);
		  }
		  
		  
		return responseList ;
	}


	@Override
	public WorkoutDatesResponse getWorkoutDates() {
	    String userId = SecurityContextHolder.getContext().getAuthentication().getName();

	    UserEntity user = userRepository.findById(userId)
	            .orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

	    List<WorkoutSessionEntity> sessions = sessionRepository.findByUser(user);

	    List<String> dates = sessions.stream()
	            .map(session -> session.getDate().toString())
	            .collect(Collectors.toList());

	    return new WorkoutDatesResponse(userId, dates);
	}


	@Override
	@Transactional
	public ApiResponse updateExerciseEntry(Long exerciseId, UpdateExerciseEntryDto exerciseEntryDto) {
		
		 String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		ExerciseEntryEntity entryEntity = exerciseEntryRepository.findById(exerciseId)
				.orElseThrow(() -> new UserServiceExceptions(ErrorMessages.EXERCISE_NOT_FOUND.getMessage()));
		
		  // Verify the session and user ownership
	    UserEntity entryOwner = entryEntity.getSession().getUser();
	    if (!entryOwner.getUserId().equals(userId)) {
	        throw new UserServiceExceptions(ErrorMessages.UNAUTHORIZED_ACCESS.getMessage());
	    }
		
		if(exerciseEntryDto.getExerciseName() != null) {
			entryEntity.setExerciseName(exerciseEntryDto.getExerciseName());
		}
		
		if(exerciseEntryDto.getTargetArea() != null) {
			entryEntity.setTargetArea(exerciseEntryDto.getTargetArea());
		}
		
		if (exerciseEntryDto.getSets() != null) {
			entryEntity.getSets().clear();
			for(ExerciseSetDto setDto : exerciseEntryDto.getSets()) {
				ExerciseSetEntity setEntity = modelMapper.map(setDto, ExerciseSetEntity.class);
				setEntity.setExerciseEntry(entryEntity);
				entryEntity.getSets().add(setEntity);
			}
		}
		
		exerciseEntryRepository.save(entryEntity);
		
		return new ApiResponse("success", SuccessResponse.EXERCISE_UPDATED_SUCCESSFULLY.getMessage());
	}
}
