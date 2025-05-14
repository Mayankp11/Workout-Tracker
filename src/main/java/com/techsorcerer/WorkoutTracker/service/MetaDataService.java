package com.techsorcerer.WorkoutTracker.service;

import com.techsorcerer.WorkoutTracker.dto.ExerciseMetaDataDto;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;

public interface MetaDataService {

	ApiResponse addExerciseData(ExerciseMetaDataDto dto);

}
