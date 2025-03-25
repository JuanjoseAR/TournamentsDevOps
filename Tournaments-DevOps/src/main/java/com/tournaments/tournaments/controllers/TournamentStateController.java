package com.tournaments.tournaments.controllers;

import com.tournaments.tournaments.entities.TournamentState;
import com.tournaments.tournaments.services.TournamentStateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournament/status")

public class TournamentStateController {
    private final TournamentStateService tournamentStateService;

    public TournamentStateController(TournamentStateService tournamentStateService) {
        this.tournamentStateService = tournamentStateService;
    }

    @GetMapping
    public List<TournamentState> getAllTournamentStates() {
        return tournamentStateService.getAllTournamentStates();
    }

    @PostMapping
    public ResponseEntity<TournamentState> createTournamentState(@RequestBody TournamentState tournamentState) {
        TournamentState createdState = tournamentStateService.createTournamentState(tournamentState);
        return ResponseEntity.ok(createdState);
    }

//    @GetMapping
//   public ResponseEntity<List<TournamentState>> getAllTournamentStates() {
//        List<TournamentState> states = tournamentStateService.getAllTournamentStates();
//        return ResponseEntity.ok(states);
//}

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TournamentState>> getTournamentStateById(@PathVariable("id") Integer id) {
        Optional<TournamentState> state = tournamentStateService.getTournamentStateById(id);
        return ResponseEntity.ok(state);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<TournamentState>> updateTournamentState(@PathVariable("id") Integer id, @RequestBody TournamentState tournamentState) {
        Optional<TournamentState> updatedState = tournamentStateService.updateTournamentStateById(id, tournamentState);
        return ResponseEntity.ok(updatedState);
    }
}
