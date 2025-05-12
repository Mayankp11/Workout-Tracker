package com.techsorcerer.WorkoutTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techsorcerer.WorkoutTracker.entity.CardioEntryEntity;

@Repository
public interface CardioEntryRepository extends JpaRepository<CardioEntryEntity, Long>{

}
