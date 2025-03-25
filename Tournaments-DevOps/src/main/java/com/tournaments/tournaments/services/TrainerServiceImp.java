package com.tournaments.tournaments.services;

import com.tournaments.tournaments.dto.TrainerDTO;
import com.tournaments.tournaments.dto.TrainerMapper;
import com.tournaments.tournaments.entities.Trainer;
import com.tournaments.tournaments.repositories.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImp implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;
    private final TeamService teamService;

    public TrainerServiceImp(TrainerRepository trainerRepository, TrainerMapper trainerMapper, TeamService teamService) {
        this.trainerRepository = trainerRepository;
        this.trainerMapper = trainerMapper;
        this.teamService = teamService;
    }

    @Override
    public Optional<TrainerDTO> getTrainerById(Integer id) {
        return trainerRepository.findById(id).map(trainerMapper::toDTO);
    }

    @Override
    public Optional<Trainer> findTrainerById(Integer id) {
        return trainerRepository.findById(id);
    }

    @Override
    public List<TrainerDTO> getAllTrainers() {
        return trainerRepository.findAll().stream()
                .map(dto->trainerMapper.toDTO(dto)).collect(Collectors.toList());
    }

    @Override
    public TrainerDTO createTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = trainerRepository.save(trainerMapper.toEntity(trainerDTO, teamService));
        return trainerMapper.toDTO(trainer);
    }

    @Override
    public Optional<TrainerDTO> updateTrainerById(Integer id, TrainerDTO trainerDTO) {
        Trainer trainer = trainerMapper.toEntity(trainerDTO, teamService);
        return trainerRepository.findById(id).map(
                trainerInBD->{
                    trainerInBD.setName(trainer.getName());
                    trainerInBD.setTeam(trainer.getTeam());

                    return trainerRepository.save(trainerInBD);
                }
        ).map(trainerMapper::toDTO);
    }

    @Override
    public void deleteTrainerById(Integer id) {
        trainerRepository.deleteById(id);
    }
}
