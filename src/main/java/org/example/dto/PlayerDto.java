package org.example.dto;

import org.example.db.entity.Player;

public class PlayerDto {
    private int playerId;
    private String playerName;
    private String position;
    private int teamId;

    public PlayerDto() {
    }

    public PlayerDto(String playerName, String position) {
        this.playerName = playerName;
        this.position = position;
    }

    public PlayerDto(String playerName, String position, int teamId) {
        this.playerName = playerName;
        this.position = position;
        this.teamId = teamId;
    }

    public PlayerDto(int playerId, String playerName, String position) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.position = position;
    }

    public PlayerDto(int playerId, String playerName, String position, int teamId) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.position = position;
        this.teamId = teamId;
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Player toEntity() {
        return getPlayerId() != 0 ? new Player(getPlayerId(), getPlayerName(), getPosition())
                : new Player(getPlayerName(), getPosition());
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", position='" + position + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
