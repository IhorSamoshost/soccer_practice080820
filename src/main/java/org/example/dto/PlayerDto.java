package org.example.dto;

import org.example.db.entity.Player;
import org.example.db.entity.Team;

public class PlayerDto {
    private int playerId;
    private String playerName;
    private String position;
    private TeamDto team;

    public PlayerDto() {
            }

    public PlayerDto(String playerName, String position) {
        this.playerName = playerName;
        this.position = position;
    }

    public PlayerDto(String playerName, String position, TeamDto team) {
        this.playerName = playerName;
        this.position = position;
        this.team = team;
    }

    public PlayerDto(int playerId, String playerName, String position, TeamDto team) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.position = position;
        this.team = team;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public TeamDto getTeam() { return team; }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    public Player toEntity(){
        return new Player(getPlayerId(), getPlayerName(), getPosition(), getTeam().toEntity());
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", position='" + position + '\'' +
                ", teamId=" + team.getTeamId() +
                '}';
    }
}
