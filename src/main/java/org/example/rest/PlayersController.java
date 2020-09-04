package org.example.rest;

import org.example.dto.PlayerDto;
import org.example.service.SoccerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "players")
public class PlayersController {
    private final SoccerService soccerService;

    public PlayersController(SoccerService soccerService) {
        this.soccerService = soccerService;
    }

    @GetMapping
    public List<PlayerDto> findAllPlayers() {
        return soccerService.findAllPlayers();
    }

    @GetMapping("{id}")
    public PlayerDto findByPlayerId(@PathVariable(value = "id") int playerId) {
        return soccerService.findPlayerById(playerId);
    }

    @PostMapping
    public String createPlayer(@RequestBody PlayerDto playerDto) {
        return soccerService.savePlayer(playerDto);
    }

    @PutMapping
    public String updatePlayer(@RequestBody PlayerDto playerDto) {
        return soccerService.updatePlayer(playerDto);
    }

    @DeleteMapping("{id}")
    public String deletePlayer(@PathVariable(value = "id") int playerId) {
        return soccerService.deletePlayer(playerId);
    }
}
