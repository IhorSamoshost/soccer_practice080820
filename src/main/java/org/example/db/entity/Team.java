package org.example.db.entity;

import org.example.dto.TeamDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "team_table")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false)
    private int teamId;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    public Team() {
    }

    public Team(String teamName, String city, String country) {
        this.teamName = teamName;
        this.city = city;
        this.country = country;
    }

    public Team(int teamId, String teamName, String city, String country) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.city = city;
        this.country = country;
    }

    public Team(String teamName, String city, String country, List<Player> players) {
        this.teamName = teamName;
        this.city = city;
        this.country = country;
        this.players = players;
    }

    public Team(int teamId, String teamName, String city, String country, List<Player> players) {
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.setTeam(null);
    }

    public TeamDto toDto() {
        return new TeamDto(getTeamId(), getTeamName(), getCity(), getCountry(),
                getPlayers().stream().map(Player::toDto).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", players=" + players +
                '}';
    }
}
