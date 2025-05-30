package com.techsorcerer.WorkoutTracker.service;

import java.util.List;

import com.techsorcerer.WorkoutTracker.dto.ExerciseMetaDataDto;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.ExercisesWithCountResponse;
import com.techsorcerer.WorkoutTracker.response.TargetAreaWithCount;

public interface MetaDataService {

	ApiResponse addExerciseData(List<ExerciseMetaDataDto> dtoList);

	List<ExerciseMetaDataDto> getAllExercises();

	List<ExerciseMetaDataDto> getExerciseByTargetArea(String targetArea);

	List<String> getAllTargetAreas();

	ExercisesWithCountResponse getAllExercisesWithCount();

	ExercisesWithCountResponse getExerciseByTargetAreaWithCount(String targetArea);

	List<TargetAreaWithCount> getAllTargetAreasWithCount();

	ApiResponse updateExerciseByNameAndTarget(ExerciseMetaDataDto dto);

	ApiResponse deleteExerciseByNameAndTarget(ExerciseMetaDataDto dto);

}
