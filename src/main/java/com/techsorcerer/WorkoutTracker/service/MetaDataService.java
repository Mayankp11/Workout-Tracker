package com.techsorcerer.WorkoutTracker.service;

import java.util.List;

import com.techsorcerer.WorkoutTracker.dto.ExerciseMetaDataDto;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;

public interface MetaDataService {

	ApiResponse addExerciseData(List<ExerciseMetaDataDto> dtoList);

	List<ExerciseMetaDataDto> getAllExercises();

}
