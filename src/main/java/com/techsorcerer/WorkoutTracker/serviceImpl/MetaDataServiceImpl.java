package com.techsorcerer.WorkoutTracker.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techsorcerer.WorkoutTracker.dto.ExerciseMetaDataDto;
import com.techsorcerer.WorkoutTracker.entity.ExerciseMetaDataEntity;
import com.techsorcerer.WorkoutTracker.exceptions.MetaDataException;
import com.techsorcerer.WorkoutTracker.exceptions.WorkoutServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.ExerciseMetaDataRepository;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.ErrorMessages;
import com.techsorcerer.WorkoutTracker.response.ExercisesWithCountResponse;
import com.techsorcerer.WorkoutTracker.response.SuccessResponse;
import com.techsorcerer.WorkoutTracker.response.TargetAreaWithCount;
import com.techsorcerer.WorkoutTracker.service.MetaDataService;

import jakarta.persistence.Entity;

@Service
public class MetaDataServiceImpl implements MetaDataService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ExerciseMetaDataRepository metaDataRepository;

	@Override
	public ApiResponse addExerciseData(List<ExerciseMetaDataDto> dtoList) {
		for (ExerciseMetaDataDto dto : dtoList) {
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
		}
		return new ApiResponse("success", SuccessResponse.DATA_ADDED_SUCCESSFULLY.getMessage());
	}

	@Override
	public List<ExerciseMetaDataDto> getAllExercises() {
		List<ExerciseMetaDataEntity> entities = metaDataRepository.findAll();
		return entities.stream().map(entity -> modelMapper.map(entity, ExerciseMetaDataDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ExerciseMetaDataDto> getExerciseByTargetArea(String targetArea) {
		List<ExerciseMetaDataEntity> entities = metaDataRepository.findByTargetAreaIgnoreCase(targetArea);
		if (entities == null) {
			throw new MetaDataException(ErrorMessages.EXERCISE_NOT_FOUND.getMessage());
		}
		return entities.stream().map(entity -> modelMapper.map(entity, ExerciseMetaDataDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getAllTargetAreas() {
		return metaDataRepository.findDistinctTargetArea();
	}

	@Override
	public ExercisesWithCountResponse getAllExercisesWithCount() {

		List<ExerciseMetaDataEntity> entities = metaDataRepository.findAll();
		List<ExerciseMetaDataDto> dtoList = entities.stream()
				.map(entity -> modelMapper.map(entity, ExerciseMetaDataDto.class)).collect(Collectors.toList());

		ExercisesWithCountResponse response = new ExercisesWithCountResponse();
		response.setTotalCount(dtoList.size());
		response.setExercises(dtoList);
		return response;

	}

	@Override
	public ExercisesWithCountResponse getExerciseByTargetAreaWithCount(String targetArea) {
		List<ExerciseMetaDataEntity> entities = metaDataRepository.findByTargetAreaIgnoreCase(targetArea);
		if (entities == null) {
			throw new MetaDataException(ErrorMessages.EXERCISE_NOT_FOUND.getMessage());
		}
		List<ExerciseMetaDataDto> dto = entities.stream().map(entity -> modelMapper.map(entity, ExerciseMetaDataDto.class))
				.collect(Collectors.toList());
		
		ExercisesWithCountResponse response = new ExercisesWithCountResponse();
		response.setTotalCount(dto.size());
		response.setExercises(dto);
		return response;
		}

	@Override
	public List<TargetAreaWithCount> getAllTargetAreasWithCount() {
		return metaDataRepository.getTargetAreasWithCounts();
	}
	
	@Override
	public ApiResponse updateExerciseByNameAndTarget(ExerciseMetaDataDto dto) {
	    if (dto.getExerciseName() == null || dto.getTargetArea() == null) {
	        throw new MetaDataException("Exercise name and target area are required to update.");
	    }

	   ExerciseMetaDataEntity entity = metaDataRepository.findByExerciseNameIgnoreCaseAndTargetAreaIgnoreCase(dto.getExerciseName(), dto.getTargetArea())
			   .orElseThrow(() -> new MetaDataException(ErrorMessages.EXERCISE_NOT_FOUND.getMessage()));

	    // Update other fields if needed (e.g., description in future)
	    entity.setExerciseName(dto.getExerciseName());
	    entity.setTargetArea(dto.getTargetArea());

	    metaDataRepository.save(entity);
	    return new ApiResponse("success", SuccessResponse.DATA_UPDATED_SUCCESSFULLY.getMessage());
	}


}
