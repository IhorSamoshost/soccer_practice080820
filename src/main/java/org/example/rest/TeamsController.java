package org.example.rest;

import org.example.dto.PlayerDto;
import org.example.dto.TeamDto;
import org.example.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "teams")
public class TeamsController {
    private final TeamService teamService;

    public TeamsController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDto> findAllTeams() {
        return teamService.findAllTeams();
    }

    @GetMapping("{id}")
    public TeamDto findByTeamId(@PathVariable(value = "id") int teamId) {
        return teamService.findTeamById(teamId);
    }

    @PostMapping
    public String createTeam(@RequestBody TeamDto teamDto) {
        return teamService.saveTeam(teamDto);
    }

    @PutMapping
    public String updateTeam(@RequestBody TeamDto teamDto) {
        return teamService.updateTeam(teamDto);
    }

    @DeleteMapping("{id}")
    public String deleteTeam(@PathVariable(value = "id") int teamId) {
        return teamService.deleteTeam(teamId);
    }

    @GetMapping(value = "check-team-complete/{id}")
    public String checkTeamComplete(@PathVariable(value = "id") int teamId) {
        return teamService.isTeamComplete(teamId);
    }

    @GetMapping(value = "get-team-players/{teamName}")
    public List<PlayerDto> getTeamPlayers(@PathVariable(value = "teamName") String teamName) {
        return teamService.findAllPlayersByTeamName(teamName);
    }

    @GetMapping(value = "get-team-players/{teamName}/{position}")
    public List<PlayerDto> getTeamPlayers(
            @PathVariable(value = "teamName") String teamName,
            @PathVariable(value = "position") String position) {
        return teamService.findAllPlayersByTeamNameAndPosition(teamName, position);
    }

}
