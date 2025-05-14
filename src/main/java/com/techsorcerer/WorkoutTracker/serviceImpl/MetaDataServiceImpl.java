package com.techsorcerer.WorkoutTracker.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.techsorcerer.WorkoutTracker.dto.ExerciseMetaDataDto;
import com.techsorcerer.WorkoutTracker.entity.ExerciseMetaDataEntity;
import com.techsorcerer.WorkoutTracker.exceptions.MetaDataException;
import com.techsorcerer.WorkoutTracker.exceptions.WorkoutServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.ExerciseMetaDataRepository;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.ErrorMessages;
import com.techsorcerer.WorkoutTracker.response.SuccessResponse;
import com.techsorcerer.WorkoutTracker.service.MetaDataService;

public class MetaDataServiceImpl implements MetaDataService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ExerciseMetaDataRepository metaDataRepository;

	@Override
	public ApiResponse addExerciseData(ExerciseMetaDataDto dto) {
		
		if (dto.getExerciseName() == null || dto.getTargetArea() == null) {
		    throw new MetaDataException(ErrorMessages.DATA_CANNOT_BE_NULL.getMessage());
		}
		
		boolean exists = metaDataRepository
			    .existsByExerciseNameIgnoreCaseAndTargetAreaIgnoreCase(dto.getExerciseName(), dto.getTargetArea());

			if (exists) {
			    throw new MetaDataException(ErrorMessages.EXERCISE_ALREADY_EXISTS.getMessage());
			}


		
		ExerciseMetaDataEntity entity = modelMapper.map(dto, ExerciseMetaDataEntity.class);
		metaDataRepository.save(entity);
		return new ApiResponse("success", SuccessResponse.DATA_ADDED_SUCCESSFULLY.getMessage());
	}

}
