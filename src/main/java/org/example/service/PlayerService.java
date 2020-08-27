package org.example.service;

import org.example.db.entity.Player;
import org.example.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> findAllPlayers();

    PlayerDto findPlayerById(int id);

    String savePlayer(PlayerDto playerDto);

    String updatePlayer(PlayerDto playerDto);

    String deletePlayer(int id);
}
