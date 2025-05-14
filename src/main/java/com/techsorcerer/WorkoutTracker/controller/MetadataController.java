package com.techsorcerer.WorkoutTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/add-data")
	public ResponseEntity<ApiResponse> addMetaData(@RequestBody List<ExerciseMetaDataDto> dtoList){
		ApiResponse response = metaDataService.addExerciseData(dtoList);
		return ResponseEntity.ok(response);
	}

}
