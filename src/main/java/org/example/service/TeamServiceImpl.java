package org.example.service;

import org.example.db.TeamRepository;
import org.example.db.entity.Player;
import org.example.db.entity.Team;
import org.example.dto.PlayerDto;
import org.example.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;


    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamDto> findAllTeams() {
        return teamRepository.findAll().stream().map(Team::toDto).collect(Collectors.toList());
    }

    @Override
    public TeamDto findTeamById(int id) {
        return teamRepository.getOne(id).toDto();
    }

    @Override
    public String saveTeam(TeamDto teamDto) {
//        Team newTeam = teamRepository.
//                saveAndFlush(new Team(teamDto.getTeamName(), teamDto.getCity(),teamDto.getCountry()));
//        int teamId = newTeam.getTeamId();
//        newTeam.setPlayers(teamDto.getPlayers().stream().map(p->{p.setTeamId(teamId); return p.toEntity();})
//                .collect(Collectors.toList()));
        return teamRepository.saveAndFlush(teamDto.toEntity()).toDto().toString()+" is saved";
//        newTeam.setPlayers(teamDto.getPlayers());
//        teamRepository.save(newTeam);
//        return teamRepository.getOne(newTeam.getTeamId()).toDto();
//        Team newTeam = teamDto.toEntity();
//        TeamDto checkTeam = teamRepository.save(teamDto.toEntity()).toDto()
//        Team newTeam = teamRepository.saveAndFlush(new Team (teamDto.getTeamName(), teamDto.getCity(), teamDto.getCountry()));
//        int newTeamId=teamRepository.findByTeamName(teamDto.getTeamName()).getTeamId();
//        List<Player> players = teamDto.getPlayers().stream().map(PlayerDto::toEntity).map(p->p.setTeamId(newTeam.getTeamId())).collect(Collectors.toList());
//        players.stream()

//        return teamRepository.saveAndFlush(teamDto.toEntity()).toDto();
//        tory.getOne(teamDto.getTeamId())) ?
//        String.format("The team %s is saved", teamDto.getTeamName()) :
//        String.format("The team %s is not saved", teamDto.getTeamName());
    }

    @Override
    public String updateTeam(TeamDto teamDto) {
        try {
            if (teamRepository.existsById(teamDto.getTeamId()))
                return teamRepository.saveAndFlush(teamDto.toEntity()).toDto().toString()+" is updated";
            else throw new IllegalArgumentException();
        } catch (IllegalArgumentException iax) {
            iax.printStackTrace();
            return String.format("Updating of team %s (id=%d) is impossible: this team is absent in DB",
                    teamDto.getTeamName(), teamDto.getTeamId());
        }
    }

    @Override
    public String deleteTeam(int id) {
        if(teamRepository.existsById(id))
        {teamRepository.deleteById(id);
            return String.format("The team with id=%d is deleted", id);}
        return String.format("Deleting of team with id=%d is impossible: this team is absent in DB", id);
    }

    @Override
    public String isTeamComplete (int teamId) {
        int playersCount = teamRepository.getPlayersCountByTeamIdParam(teamId);
        if(teamRepository.existsById(teamId))
        return playersCount>=11 ?
               String.format("There are %d players in team with id=%d, so this team is complete", playersCount, teamId) :
               String.format("There are %d players in team with id=%d, so this team is not complete", playersCount, teamId);
        return String.format("Completeness check of team with id=%d is impossible: this team is absent in DB", teamId);
    }

    @Override
    public List<PlayerDto> findAllPlayersByTeamName(String teamName) {
        if(teamRepository.existsById(teamRepository.findByTeamName(teamName).getTeamId()))
        return teamRepository.findAllPlayersByTeamId(teamRepository.findByTeamName(teamName).getTeamId())
                .stream().map(Player::toDto).collect(Collectors.toList());
        return null;
    }

    @Override
    public List<PlayerDto> findAllPlayersByTeamNameAndPosition(String teamName, String position) {
        if(teamRepository.existsById(teamRepository.findByTeamName(teamName).getTeamId()))
        return teamRepository.findAllPlayersByTeamIdAndPosition(teamRepository.findByTeamName(teamName).getTeamId(), position)
                .stream().map(Player::toDto).collect(Collectors.toList());
        return null;
    }
}
