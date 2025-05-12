package com.techsorcerer.WorkoutTracker.serviceImpl;

import java.security.KeyStore.Entry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techsorcerer.WorkoutTracker.dto.CardioEntryDto;
import com.techsorcerer.WorkoutTracker.dto.ExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.ExerciseSetDto;
import com.techsorcerer.WorkoutTracker.dto.UpdateExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.CardioEntryEntity;
import com.techsorcerer.WorkoutTracker.entity.ExerciseEntryEntity;
import com.techsorcerer.WorkoutTracker.entity.ExerciseSetEntity;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;
import com.techsorcerer.WorkoutTracker.exceptions.AccessDeniedException;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;
import com.techsorcerer.WorkoutTracker.exceptions.WorkoutServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.CardioEntryRepository;
import com.techsorcerer.WorkoutTracker.repository.ExerciseEntryRepository;
import com.techsorcerer.WorkoutTracker.repository.UserRepository;
import com.techsorcerer.WorkoutTracker.repository.WorkoutSessionRepository;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.CardioEntryResponse;
import com.techsorcerer.WorkoutTracker.response.ErrorMessages;
import com.techsorcerer.WorkoutTracker.response.GroupedExerciseEntryResponse;
import com.techsorcerer.WorkoutTracker.response.SuccessResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDateResponse;
import com.techsorcerer.WorkoutTracker.response.WorkoutDayResponse;
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
    
    @Autowired
    private CardioEntryRepository cardioEntryRepository;

    @Override
    @Transactional
    public ApiResponse createWorkout(WorkoutSessionDto workoutSessionDto) {
    	//userId fetched
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new WorkoutServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

        LocalDate date = workoutSessionDto.getDate() != null 
        		? workoutSessionDto.getDate()
				 : LocalDate.now();
		 
		 if(date.isAfter(LocalDate.now())) {
			 throw new WorkoutServiceExceptions(ErrorMessages.INVALID_DATE.getMessage());
		 }

        WorkoutSessionEntity session = sessionRepository.findByUserAndDate(user, date).orElse(null);

        if (session == null) {
            session = new WorkoutSessionEntity();
            session.setUser(user);
            session.setDate(date);
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
	public WorkoutDateResponse getWorkoutDates() {
	    String userId = SecurityContextHolder.getContext().getAuthentication().getName();

	    UserEntity user = userRepository.findById(userId)
	            .orElseThrow(() -> new WorkoutServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

	    List<WorkoutSessionEntity> sessions = sessionRepository.findByUser(user);

	    List<String> dates = sessions.stream()
	            .map(session -> session.getDate().toString())
	            .collect(Collectors.toList());

	    return new WorkoutDateResponse(userId,dates);
	    
	}
	
	@Override
	public ApiResponse updateStrengthEntry(Long exerciseId, ExerciseEntryDto exerciseEntryDto) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		ExerciseEntryEntity entryEntity = exerciseEntryRepository.findById(exerciseId)
				.orElseThrow(() -> new WorkoutServiceExceptions(ErrorMessages.EXERCISE_NOT_FOUND.getMessage()));
		
		//validate ownership
		 UserEntity user = entryEntity.getSession().getUser();
		 if (!user.getUserId().equals(userId)) {
		        throw new AccessDeniedException(ErrorMessages.UNAUTHORIZED_ACCESS.getMessage());
		 }
		 
		 // apply changes
		 
		 if(exerciseEntryDto.getExerciseName() != null) {
			 entryEntity.setExerciseName(exerciseEntryDto.getExerciseName());
		 }
		 if(exerciseEntryDto.getTargetArea() != null) {
			 entryEntity.setTargetArea(exerciseEntryDto.getTargetArea());
		 }
		 if(exerciseEntryDto.getSets() != null) {
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
	
	@Override
	public ApiResponse updateCardioEntry(Long exerciseId, CardioEntryDto cardioDto	) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		CardioEntryEntity cardioEntity = cardioEntryRepository.findById(exerciseId)
				.orElseThrow(() -> new WorkoutServiceExceptions(ErrorMessages.WORKOUT_NOT_FOUND.getMessage()));
		
		UserEntity user = cardioEntity.getSession().getUser();
		if(!user.getUserId().equals(userId)) {
			throw new AccessDeniedException(ErrorMessages.UNAUTHORIZED_ACCESS.getMessage());
		}
		
		if(cardioDto.getCardioType() != null) {
			cardioEntity.setCardioType(cardioDto.getCardioType());
		}
		cardioEntity.setDurationMinutes(cardioDto.getDurationMinutes());
		cardioEntity.setMiles(cardioDto.getMiles());
		cardioEntity.setLevel(cardioDto.getLevel());
		cardioEntity.setSpeed(cardioDto.getSpeed());
		
		cardioEntryRepository.save(cardioEntity);
		return new ApiResponse("success", SuccessResponse.CARDIO_UPDATED_SUCCESSFULLY.getMessage());
	}

	@Override
	public ApiResponse deleteWorkout(Long exerciseId) {
		//userId fetched
	    String userId = SecurityContextHolder.getContext().getAuthentication().getName();

	    ExerciseEntryEntity entryEntity = exerciseEntryRepository.findById(exerciseId)
	    		.orElseThrow(() -> new WorkoutServiceExceptions(ErrorMessages.WORKOUT_NOT_FOUND.getMessage()));
	    
	    // Check if entry belong to the correct user or not
	    if(!entryEntity.getSession().getUser().getUserId().equals(userId)) {
	    	throw new AccessDeniedException(ErrorMessages.UNAUTHORIZED_ACCESS.getMessage());
	    }
	    
	    exerciseEntryRepository.delete(entryEntity);
	    
		return new ApiResponse("success", SuccessResponse.EXERCISE_DELETED_SUCCESSFULLY.getMessage());
	}


	@Override
	public ApiResponse addCardioWorkout(CardioEntryDto cardioEntryDto) {
		 String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		 
		 UserEntity user = userRepository.findById(userId)
				 .orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));
		 
		 LocalDate date = cardioEntryDto.getDate() != null
				 ? cardioEntryDto.getDate()
				 : LocalDate.now();
		 
		 if(date.isAfter(LocalDate.now())) {
			 throw new WorkoutServiceExceptions(ErrorMessages.INVALID_DATE.getMessage());
		 }
		 
		 WorkoutSessionEntity session = sessionRepository.findByUserAndDate(user, date)
	                .orElseGet(() -> {
	                    WorkoutSessionEntity newSession = new WorkoutSessionEntity();
	                    newSession.setUser(user);
	                    newSession.setDate(date);
	                    newSession.setExercises(new ArrayList<>());
	                    newSession.setCardioEntries(null);
	                    return sessionRepository.save(newSession);
	                });
		 
		 CardioEntryEntity cardioEntryEntity = modelMapper.map(cardioEntryDto, CardioEntryEntity.class);
		 cardioEntryEntity.setSession(session);
		 session.getCardioEntries().add(cardioEntryEntity);
		 sessionRepository.save(session);
		 
		 return new ApiResponse("success", SuccessResponse.WORKOUT_SAVED.getMessage());
	}
	
	


	@Override
	public WorkoutDayResponse getAllWorkoutForDate(String date) {
 String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		 
		 UserEntity user = userRepository.findById(userId)
				 .orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));
		 
		 LocalDate today = LocalDate.parse(date);
		 
		 WorkoutSessionEntity sessionEntity = sessionRepository.findByUserAndDate(user, today)
				 .orElseThrow(() -> new WorkoutServiceExceptions(ErrorMessages.WORKOUT_NOT_FOUND.getMessage()));
		 
		 // Add strength entries
		 List<GroupedExerciseEntryResponse> strengthEntries = sessionEntity.getExercises()
				 .stream()
			        .map(entry -> {
					 GroupedExerciseEntryResponse gr = new GroupedExerciseEntryResponse();
					 gr.setUserId(userId);
					 gr.setExerciseId(entry.getId());
					 gr.setExerciseName(entry.getExerciseName());
					 gr.setTargetArea(entry.getTargetArea());
					 
					 List<Integer> sets = new ArrayList<>();
					 List<Double> weights= new ArrayList<>();
					 List<Integer> reps = new ArrayList<>();
					 for(ExerciseSetEntity s : entry.getSets()) {
						 sets.add(s.getSetNumber());
						 weights.add(s.getWeight());
						 reps.add(s.getReps());
					 }
					 
					 gr.setSets(sets);
					 gr.setWeights(weights);
					 gr.setReps(reps);
					 return gr;
				 })
			        .collect(Collectors.toList());
		 
		 
		 // Add Cardio entries
		 List<CardioEntryResponse> cardioEntries = sessionEntity.getCardioEntries()
				 .stream()
				 	.map(entry -> {
				 		CardioEntryResponse cr = new CardioEntryResponse();
				 		cr.setCardioEntryId((entry.getId()));
				 		cr.setCardioType((entry.getCardioType()));
				 		cr.setLevel(entry.getLevel());
				 		cr.setSpeed(entry.getSpeed());
				 		cr.setDurationMinutes(entry.getDurationMinutes());
				 		cr.setMiles(entry.getMiles());
				 		return cr;
				 	})
				 	.collect(Collectors.toList());
				 
		WorkoutDayResponse response = new WorkoutDayResponse();
		response.setUserId(userId);
		response.setDate(today);
		response.setCardioEntries(cardioEntries);
		response.setStrengthEntries(strengthEntries);
		return response;
	}

	@Override
	public ApiResponse deleteCardio(Long exerciseId) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		CardioEntryEntity cardioEntity = cardioEntryRepository.findById(exerciseId)
				.orElseThrow(() -> new WorkoutServiceExceptions(ErrorMessages.WORKOUT_NOT_FOUND.getMessage()));
		
		UserEntity user = cardioEntity.getSession().getUser();
		if(!user.getUserId().equals(userId)) {
			throw new AccessDeniedException(ErrorMessages.UNAUTHORIZED_ACCESS.getMessage());
		}
		
		cardioEntryRepository.delete(cardioEntity);

		return new ApiResponse("Success", SuccessResponse.EXERCISE_DELETED_SUCCESSFULLY.getMessage());
	}

	

	
}
