package com.techsorcerer.WorkoutTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.ExerciseMetaDataDto;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.service.MetaDataService;

@RestController
@RequestMapping("api/metadata")
public class MetadataController {
	
	@Autowired
	MetaDataService metaDataService;

	public ResponseEntity<ApiResponse> addMetaData(ExerciseMetaDataDto dto){
		ApiResponse response = metaDataService.addExerciseData(dto);
		return ResponseEntity.ok(response);
	}

}
