package org.example.rest;

import org.example.dto.PlayerDto;
import org.example.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "players")
public class PlayersController {
    private final PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDto> findAllPlayers() {
        return playerService.findAllPlayers();
    }

    @GetMapping("{id}")
    public PlayerDto findByPlayerId(@PathVariable(value = "id") int playerId) {
        return playerService.findPlayerById(playerId);
    }

    @PostMapping
    public String createPlayer(@RequestBody PlayerDto playerDto) {
        return playerService.savePlayer(playerDto);
    }

    @PutMapping
    public String updatePlayer(@RequestBody PlayerDto playerDto) {
        return playerService.updatePlayer(playerDto);
    }

    @DeleteMapping("{id}")
    public String deletePlayer(@PathVariable(value = "id") int playerId) {
        return playerService.deletePlayer(playerId);
    }
}
