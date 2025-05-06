package com.techsorcerer.WorkoutTracker.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techsorcerer.WorkoutTracker.dto.ExerciseEntryDto;
import com.techsorcerer.WorkoutTracker.dto.ExerciseSetDto;
import com.techsorcerer.WorkoutTracker.dto.WorkoutSessionDto;
import com.techsorcerer.WorkoutTracker.entity.ExerciseEntryEntity;
import com.techsorcerer.WorkoutTracker.entity.ExerciseSetEntity;
import com.techsorcerer.WorkoutTracker.entity.UserEntity;
import com.techsorcerer.WorkoutTracker.entity.WorkoutSessionEntity;
import com.techsorcerer.WorkoutTracker.exceptions.UserServiceExceptions;
import com.techsorcerer.WorkoutTracker.repository.UserRepository;
import com.techsorcerer.WorkoutTracker.repository.WorkoutSessionRepository;
import com.techsorcerer.WorkoutTracker.response.ErrorMessages;
import com.techsorcerer.WorkoutTracker.security.JwtUtil;
import com.techsorcerer.WorkoutTracker.service.WorkoutService;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutSessionRepository sessionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public WorkoutSessionEntity createWorkout(WorkoutSessionDto workoutSessionDto) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserServiceExceptions(ErrorMessages.USER_NOT_FOUND.getMessage()));

        LocalDate today = LocalDate.now();

        // Try to find existing session for today
        WorkoutSessionEntity session = sessionRepository.findByUserAndDate(user, today).orElse(null);

        if (session == null) {
            session = new WorkoutSessionEntity();
            session.setUser(user);
            session.setDate(today);
            session.setExercises(new ArrayList<>());
        }

        List<ExerciseEntryEntity> newEntries = new ArrayList<>();

        for (ExerciseEntryDto dto : workoutSessionDto.getExercises()) {
            ExerciseEntryEntity entry = modelMapper.map(dto, ExerciseEntryEntity.class);
            entry.setSession(session);

            List<ExerciseSetEntity> setEntities = new ArrayList<>();
            for (ExerciseSetDto setDto : dto.getSets()) {
                ExerciseSetEntity set = modelMapper.map(setDto, ExerciseSetEntity.class);
                set.setExerciseEntry(entry);
                setEntities.add(set);
            }

            entry.setSets(setEntities);
            newEntries.add(entry);
        }

        session.getExercises().addAll(newEntries);
        sessionRepository.save(session);

        return session;
    }
}
