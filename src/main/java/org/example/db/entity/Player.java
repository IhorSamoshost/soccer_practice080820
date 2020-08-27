package org.example.db.entity;

import org.example.dto.PlayerDto;

import javax.persistence.*;
import java.util.Objects;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public PlayerDto toDto(){
        return new PlayerDto(getPlayerId(), getPlayerName(), getPosition(), getTeam().toDto());
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Player)) return false;
//        Player player = (Player) o;
//        return getPlayerId() == player.getPlayerId() &&
//                getTeamId() == player.getTeamId() &&
//                getPlayerName().equals(player.getPlayerName()) &&
//                Objects.equals(getPosition(), player.getPosition());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getPlayerId(), getPlayerName(), getPosition(), getTeamId());
//    }

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
