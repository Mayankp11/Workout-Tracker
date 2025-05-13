package com.techsorcerer.WorkoutTracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techsorcerer.WorkoutTracker.dto.ExerciseNameDto;
import com.techsorcerer.WorkoutTracker.dto.TargetAreaDto;
import com.techsorcerer.WorkoutTracker.repository.ExerciseNameRepository;
import com.techsorcerer.WorkoutTracker.repository.TargetAreaRepository;

@RestController
@RequestMapping("api/metadata")
public class MetadataController {

	private final TargetAreaRepository targetAreaRepository;
	private final ExerciseNameRepository exerciseNameRepository;

	public MetadataController(TargetAreaRepository targetAreaRepository,
			ExerciseNameRepository exerciseNameRepository) {
		this.exerciseNameRepository = exerciseNameRepository;
		this.targetAreaRepository = targetAreaRepository;
	}

	@GetMapping("/target-areas")
	public ResponseEntity<List<TargetAreaDto>> getAllTargetAreas() {
		var areas = targetAreaRepository.findAll().stream().map(a -> new TargetAreaDto(a.getId(), a.getLabel()))
				.toList();
		return ResponseEntity.ok(areas);
	}
	
	// 1b) Get exercises by target area
    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseNameDto>> getExercisesByArea(
            @RequestParam("targetAreaId") Long targetAreaId
    ) {
        var exercises = exerciseNameRepository.findByTargetAreaId(targetAreaId).stream()
            .map(e -> new ExerciseNameDto(e.getId(), e.getLabel()))
            .toList();
        return ResponseEntity.ok(exercises);
    }

}
