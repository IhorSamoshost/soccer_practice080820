package org.example.rest;

import org.example.dto.PlayerDto;
import org.example.dto.TeamDto;
import org.example.service.SoccerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "teams")
public class TeamsController {
    private final SoccerService soccerService;

    public TeamsController(SoccerService soccerService) {
        this.soccerService = soccerService;
    }

    @GetMapping
    public List<TeamDto> findAllTeams() {
        return soccerService.findAllTeams();
    }

    @GetMapping("{id}")
    public TeamDto findByTeamId(@PathVariable(value = "id") int teamId) {
        return soccerService.findTeamById(teamId);
    }

    @GetMapping(value = "check-team-complete/{id}")
    public String checkTeamComplete(@PathVariable(value = "id") int teamId) {
        return soccerService.isTeamComplete(teamId);
    }

    @GetMapping(value = "get-team-players/{teamName}")
    public List<PlayerDto> getTeamPlayers(@PathVariable(value = "teamName") String teamName) {
        return soccerService.findAllPlayersByTeamName(teamName);
    }

    @GetMapping(value = "get-team-players/{teamName}/{position}")
    public List<PlayerDto> getTeamPlayers(
            @PathVariable(value = "teamName") String teamName,
            @PathVariable(value = "position") String position) {
        return soccerService.findAllPlayersByTeamNameAndPosition(teamName, position);
    }

    @PostMapping
    public String createTeam(@RequestBody TeamDto teamDto) {
        return soccerService.saveTeam(teamDto);
    }

    @PutMapping
    public String updateTeam(@RequestBody TeamDto teamDto) {
        return soccerService.updateTeam(teamDto);
    }

    @DeleteMapping("{id}")
    public String deleteTeam(@PathVariable(value = "id") int teamId) {
        return soccerService.deleteTeam(teamId);
    }


}
