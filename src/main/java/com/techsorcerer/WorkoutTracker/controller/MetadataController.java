package com.techsorcerer.WorkoutTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.ExerciseMetaDataDto;
import com.techsorcerer.WorkoutTracker.response.ApiResponse;
import com.techsorcerer.WorkoutTracker.response.ExercisesWithCountResponse;
import com.techsorcerer.WorkoutTracker.response.TargetAreaWithCount;
import com.techsorcerer.WorkoutTracker.service.MetaDataService;

@PreAuthorize("hasRole('ADMIN')")
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
	
//	@GetMapping("/exercises")
//	public ResponseEntity<List<ExerciseMetaDataDto>> getAllExercises(){
//		List<ExerciseMetaDataDto> response = metaDataService.getAllExercises();
//		return ResponseEntity.ok(response);
//	}
	@GetMapping("/exercises")
	public ResponseEntity<ExercisesWithCountResponse> getAllExercisesWithCount(){
		ExercisesWithCountResponse response = metaDataService.getAllExercisesWithCount();
		return ResponseEntity.ok(response);
	}
	
//	@GetMapping("/exercises/by-target")
//	public ResponseEntity<List<ExerciseMetaDataDto>> getExerciseByTargetArea(@RequestParam String targetArea){
//		List<ExerciseMetaDataDto> response = metaDataService.getExerciseByTargetArea(targetArea);
//		return ResponseEntity.ok(response);
//	}
	
	@GetMapping("/exercises/by-target")
	public ResponseEntity<ExercisesWithCountResponse> getExerciseByTargetAreaWihCount(@RequestParam String targetArea){
		ExercisesWithCountResponse response = metaDataService.getExerciseByTargetAreaWithCount(targetArea);
		return ResponseEntity.ok(response);
	}
	
	
//	@GetMapping("/exercises/target-area")
//	public ResponseEntity<List<String>> getTargetArea(){
//		List<String> response = metaDataService.getAllTargetAreas();
//		return ResponseEntity.ok(response);
//	}
	
	@GetMapping("/exercises/target-area")
	public ResponseEntity<List<TargetAreaWithCount>> getAllTargetAreaWithCount(){
		List<TargetAreaWithCount> response = metaDataService.getAllTargetAreasWithCount();
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/exercises/update")
	public ResponseEntity<ApiResponse> updateExercise(@RequestBody ExerciseMetaDataDto dto) {
	    ApiResponse response = metaDataService.updateExerciseByNameAndTarget(dto);
	    return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/exercises/delete")
	public ResponseEntity<ApiResponse> deleteExerciseByNameAndTarget(@RequestBody ExerciseMetaDataDto dto) {
	    ApiResponse response = metaDataService.deleteExerciseByNameAndTarget(dto);
	    return ResponseEntity.ok(response);
	}





}
