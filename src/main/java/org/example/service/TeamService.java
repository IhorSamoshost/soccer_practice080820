package org.example.service;

import org.example.db.PlayerRepository;
import org.example.dto.PlayerDto;
import org.example.dto.TeamDto;

import java.util.List;

public interface TeamService {

    List<TeamDto> findAllTeams();

    TeamDto findTeamById(int id);

    String saveTeam(TeamDto teamDto);

    String updateTeam(TeamDto teamDto);

    String deleteTeam(int id);

    String isTeamComplete (int id);

    List<PlayerDto> findAllPlayersByTeamName(String teamName);

    List<PlayerDto> findAllPlayersByTeamNameAndPosition(String teamName, String position);
}
