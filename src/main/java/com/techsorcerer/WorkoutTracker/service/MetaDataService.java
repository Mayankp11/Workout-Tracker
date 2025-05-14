package com.techsorcerer.WorkoutTracker.service;

import java.util.List;

import com.techsorcerer.WorkoutTracker.dto.ExerciseMetaDataDto;
import com.techsorcerer.WorkoutTracker.dto.ExercisesWithCountDto;
import com.techsorcerer.WorkoutTracker.dto.TargetAreaWithCount;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;

public interface MetaDataService {

	ApiResponse addExerciseData(List<ExerciseMetaDataDto> dtoList);

	List<ExerciseMetaDataDto> getAllExercises();

	List<ExerciseMetaDataDto> getExerciseByTargetArea(String targetArea);

	List<String> getAllTargetAreas();

	ExercisesWithCountDto getAllExercisesWithCount();

	ExercisesWithCountDto getExerciseByTargetAreaWithCount(String targetArea);

	List<TargetAreaWithCount> getAllTargetAreasWithCount();

}
