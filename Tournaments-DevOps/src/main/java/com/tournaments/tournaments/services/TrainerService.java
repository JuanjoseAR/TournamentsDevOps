package com.tournaments.tournaments.services;

import com.tournaments.tournaments.dto.TrainerDTO;
import com.tournaments.tournaments.entities.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {
    Optional<TrainerDTO> getTrainerById(Integer id);
    Optional<Trainer> findTrainerById(Integer id);
    List<TrainerDTO> getAllTrainers();
    TrainerDTO createTrainer(TrainerDTO trainerDTO);
    Optional<TrainerDTO> updateTrainerById(Integer id, TrainerDTO trainerDTO);
    void deleteTrainerById(Integer id);
}
