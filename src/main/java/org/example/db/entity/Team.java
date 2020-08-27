package org.example.db.entity;

import org.example.dto.TeamDto;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
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

    @JoinColumn(name = "id_team")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Player> players;

    public Team() {
    }

    public Team(String teamName, String city, String country) {
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

    public TeamDto toDto (){
        return new TeamDto(getTeamId(), getTeamName(), getCity(), getCountry(),
                getPlayers().stream().map(Player::toDto).collect(Collectors.toList()));
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Team)) return false;
//        Team team = (Team) o;
//        return getTeamId() == team.getTeamId() &&
//                getTeamName().equals(team.getTeamName()) &&
//                Objects.equals(getCity(), team.getCity()) &&
//                Objects.equals(getCountry(), team.getCountry()) &&
//                Objects.equals(getPlayers(), team.getPlayers());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getTeamId(), getTeamName(), getCity(), getCountry(), getPlayers());
//    }

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
