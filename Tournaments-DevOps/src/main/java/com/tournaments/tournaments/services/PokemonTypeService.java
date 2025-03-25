package com.tournaments.tournaments.services;

import com.tournaments.tournaments.entities.PokemonType;

import java.util.List;
import java.util.Optional;

public interface PokemonTypeService {
    Optional<PokemonType> getPokemonTypeById(Integer id);
    List<PokemonType> getAllPokemonTypes();
    PokemonType createPokemonType(PokemonType pokemonType);
    Optional<PokemonType> updatePokemonTypeById(Integer id, PokemonType pokemonType);
    void deletePokemonTypeById(Integer id);
}
