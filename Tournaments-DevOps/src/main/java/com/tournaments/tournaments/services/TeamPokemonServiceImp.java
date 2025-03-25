package com.tournaments.tournaments.services;

import com.tournaments.tournaments.dto.TeamPokemonDTO;
import com.tournaments.tournaments.dto.TeamPokemonMapper;
import com.tournaments.tournaments.entities.TeamPokemon;
import com.tournaments.tournaments.repositories.TeamPokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamPokemonServiceImp implements TeamPokemonService {

    private final TeamPokemonRepository teamPokemonRepository;
    private final TeamPokemonMapper teamPokemonMapper;
    private final TeamService teamService;
    private final PokemonService pokemonService;

    public TeamPokemonServiceImp(TeamPokemonRepository teamPokemonRepository, TeamPokemonMapper teamPokemonMapper, PokemonService pokemonService, TeamService teamService) {
        this.teamPokemonRepository = teamPokemonRepository;
        this.teamPokemonMapper = teamPokemonMapper;
        this.pokemonService = pokemonService;
        this.teamService = teamService;
    }

    @Override
    public Optional<TeamPokemonDTO> getTeamPokemonById(Integer id) {
        return teamPokemonRepository.findById(id).map(teamPokemonMapper::toDTO);
    }

    @Override
    public Optional<TeamPokemon> findTeamPokemonById(Integer id) {
        return teamPokemonRepository.findById(id);
    }

    @Override
    public List<TeamPokemonDTO> getAllTeamsPokemon() {
        return teamPokemonRepository.findAll().stream()
                .map(dto->teamPokemonMapper.toDTO(dto)).collect(Collectors.toList());
    }

    @Override
    public TeamPokemonDTO createTeamPokemon(TeamPokemonDTO teamPokemonDTO) {
        TeamPokemon teamPokemon = teamPokemonMapper.toEntity(teamPokemonDTO, teamService, pokemonService);
        return teamPokemonMapper.toDTO(teamPokemon);
    }

    @Override
    public Optional<TeamPokemonDTO> updateTeamPokemonById(Integer id, TeamPokemonDTO teamPokemonDTO) {
        TeamPokemon newTeamPoke = teamPokemonMapper.toEntity(teamPokemonDTO, teamService, pokemonService);
        return teamPokemonRepository.findById(id).map(
                teamPokeInBD->{
                    teamPokeInBD.setTeam(newTeamPoke.getTeam());
                    teamPokeInBD.setPokemon(newTeamPoke.getPokemon());

                    return teamPokemonRepository.save(teamPokeInBD);
                }
        ).map(teamPokemonMapper::toDTO);
    }

    @Override
    public void deleteTeamPokemonById(Integer id) {
        teamPokemonRepository.deleteById(id);
    }
}
