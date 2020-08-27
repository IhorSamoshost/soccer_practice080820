package org.example.service;

import org.example.db.PlayerRepository;
import org.example.db.entity.Player;
import org.example.dto.PlayerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    @Override
    public List<PlayerDto> findAllPlayers() {
        return playerRepository.findAll().stream().map(Player::toDto).collect(Collectors.toList());
    }

    @Override
    public PlayerDto findPlayerById(int id) {
        return playerRepository.getOne(id).toDto();
    }

    @Override
    public String savePlayer(PlayerDto playerDto) {
        return playerRepository.saveAndFlush(playerDto.toEntity()).toDto().toString()+" is saved";
    }

    @Override
    public String updatePlayer(PlayerDto playerDto) {
        try {if (playerRepository.existsById(playerDto.getPlayerId()))
            return playerRepository.saveAndFlush(playerDto.toEntity()).toDto().toString()+" is updated";
        else throw new IllegalArgumentException();
        } catch (IllegalArgumentException iax) {
            iax.printStackTrace();
            return String.format("Updating of player %s (id=%d) is impossible: this player is absent in DB",
                    playerDto.getPlayerName(), playerDto.getPlayerId());
        }
    }

    @Override
    public String deletePlayer(int id) {
        if (playerRepository.existsById(id))
        {playerRepository.deleteById(id);
        return String.format("The player with id=%d is deleted", id);}
        else return String.format("Deleting of player with id=%d is impossible: this player is absent in DB", id);
    }
}
