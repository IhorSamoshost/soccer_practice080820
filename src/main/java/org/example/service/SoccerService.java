package org.example.service;

import org.example.db.PlayerRepository;
import org.example.db.TeamRepository;
import org.example.db.entity.Player;
import org.example.db.entity.Team;
import org.example.dto.PlayerDto;
import org.example.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoccerService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public SoccerService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public List<TeamDto> findAllTeams() {
        return teamRepository.findAll().stream().map(Team::toDto).collect(Collectors.toList());
    }

    public TeamDto findTeamById(int id) {
        return teamRepository.existsById(id) ? teamRepository.getOne(id).toDto() :
                null;
    }

    public String saveTeam(TeamDto teamDto) {
        if (teamRepository.existsById(teamDto.getTeamId()))
            return String.format("Creating of team with id=%d is impossible: this team already exists in DB",
                    teamDto.getTeamId());
        Team newTeam = teamRepository.save(new Team(teamDto.getTeamName(), teamDto.getCity(), teamDto.getCountry()));
        if (teamDto.getPlayers() != null)
            teamDto.getPlayers().forEach(playerDto -> newTeam.addPlayer(playerDto.toEntity()));
        return teamRepository.save(newTeam).toDto().toString() + " is saved";
    }

    public String updateTeam(TeamDto teamDto) {
        Team newTeam;
        if (teamRepository.existsById(teamDto.getTeamId())) {
            newTeam = teamRepository.save(new Team(teamDto.getTeamId(), teamDto.getTeamName(), teamDto.getCity(),
                    teamDto.getCountry()));
            if (teamDto.getPlayers() != null)
                teamDto.getPlayers().forEach(playerDto -> newTeam.addPlayer(playerDto.toEntity()));
            return teamRepository.save(newTeam).toDto().toString() + " is updated";
        }
        return String.format("Updating of team %s (id=%d) is impossible: this team is absent in DB",
                teamDto.getTeamName(), teamDto.getTeamId());
    }

    public String deleteTeam(int id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return String.format("The team with id=%d is deleted", id);
        }
        return String.format("Deleting of team with id=%d is impossible: this team is absent in DB", id);
    }

    public String isTeamComplete(int teamId) {
        int playersCount = playerRepository.countByTeam_TeamId(teamId);
        if (teamRepository.existsById(teamId))
            return playersCount >= 11 ?
                    String.format("There are %d players in team with id=%d, so this team is complete", playersCount, teamId) :
                    String.format("There are %d players in team with id=%d, so this team is not complete", playersCount, teamId);
        return String.format("Completeness check of team with id=%d is impossible: this team is absent in DB", teamId);
    }

    public List<PlayerDto> findAllPlayersByTeamName(String teamName) {
        if (teamRepository.findByTeamName(teamName) != null)
            return playerRepository.findAllByTeam_TeamName(teamName)
                    .stream().map(Player::toDto).collect(Collectors.toList());
        return new ArrayList<>();
    }

    public List<PlayerDto> findAllPlayersByTeamNameAndPosition(String teamName, String position) {
        if (teamRepository.findByTeamName(teamName) != null)
            return playerRepository.findAllByTeam_TeamNameAndPosition(teamName, position)
                    .stream().map(Player::toDto).collect(Collectors.toList());
        return new ArrayList<>();
    }

    public List<PlayerDto> findAllPlayers() {
        return playerRepository.findAll().stream().map(Player::toDto).collect(Collectors.toList());
    }

    public PlayerDto findPlayerById(int id) {
        return playerRepository.getOne(id).toDto();
    }

    public String savePlayer(PlayerDto playerDto) {
        if (playerRepository.existsById(playerDto.getPlayerId()) || !teamRepository.existsById(playerDto.getTeamId()))
            return String.format("Creating of player with id=%d and team id=%d is impossible",
                    playerDto.getPlayerId(), playerDto.getTeamId());
        Player newPlayer = new Player(playerDto.getPlayerName(), playerDto.getPosition(),
                teamRepository.getOne(playerDto.getTeamId()));
        return playerRepository.save(newPlayer).toDto().toString() + " is saved";
    }

    public String updatePlayer(PlayerDto playerDto) {
        if (!playerRepository.existsById(playerDto.getPlayerId()) || !teamRepository.existsById(playerDto.getTeamId()))
            return String.format("Updating of player %s (id=%d) of team with id=%d is impossible",
                    playerDto.getPlayerName(), playerDto.getPlayerId(), playerDto.getTeamId());
        return playerRepository.save(new Player(playerDto.getPlayerId(), playerDto.getPlayerName(),
                playerDto.getPosition(), teamRepository.getOne(playerDto.getTeamId())))
                .toDto().toString() + " is updated";
    }

    public String deletePlayer(int id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return String.format("The player with id=%d is deleted", id);
        } else return String.format("Deleting of player with id=%d is impossible: this player is absent in DB", id);
    }
}
