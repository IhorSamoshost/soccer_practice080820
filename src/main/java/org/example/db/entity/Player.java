package org.example.db.entity;

import org.example.dto.PlayerDto;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "player_table")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", nullable = false)
    private int playerId;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "position")
    private String position;

    @ManyToOne()
    @JoinColumn(name = "id_team")
    private Team team;

    public Player() {
    }

    public Player(String playerName, String position) {
        this.playerName = playerName;
        this.position = position;
    }

    public Player(int playerId, String playerName, String position) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.position = position;
    }

    public Player(int playerId, String playerName, String position, Team team) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.position = position;
        this.team = team;
    }

    public Player(String playerName, String position, Team team) {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public PlayerDto toDto() {
        return this.team != null ?
                new PlayerDto(getPlayerId(), getPlayerName(), getPosition(), this.team.getTeamId()) :
                new PlayerDto(getPlayerId(), getPlayerName(), getPosition());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        return playerId == ((Player) o).getPlayerId();
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", position='" + position + '\'' +
                ", teamId=" + team.getTeamId() +
                '}';
    }
}
