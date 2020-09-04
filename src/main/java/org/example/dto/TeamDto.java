package org.example.dto;

import org.example.db.entity.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamDto {
    private int teamId;
    private String teamName;
    private String city;
    private String country;
    private List<PlayerDto> players;

    public TeamDto() {
    }

    public TeamDto(String teamName, String city, String country) {
        this.teamName = teamName;
        this.city = city;
        this.country = country;
    }

    public TeamDto(String teamName, String city, String country, List<PlayerDto> players) {
        this.teamName = teamName;
        this.city = city;
        this.country = country;
        this.players = players;
    }

    public TeamDto(int teamId, String teamName, String city, String country, List<PlayerDto> players) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.city = city;
        this.country = country;
        this.players = players;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    public Team toEntity() {
        return getPlayers() != null ? new Team(getTeamId(), getTeamName(), getCity(), getCountry(),
                getPlayers().stream().map(PlayerDto::toEntity).collect(Collectors.toList())) :
                new Team(getTeamId(), getTeamName(), getCity(), getCountry(), new ArrayList<>());
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", players=" + players +
                '}';
    }
}
