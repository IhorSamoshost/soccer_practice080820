package org.example.db;

import org.example.db.entity.Player;
import org.example.db.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Transactional
    Team findByTeamName(String teamName);

    @Transactional
    @Query(value = "select count(player_table.id_team) from player_table where player_table.id_team=:idTeam", nativeQuery = true)
    int getPlayersCountByTeamIdParam(@Param("idTeam") int teamId);

    @Transactional
//    @Query(value = "select * from player_table where player_table.id_team=:idTeam", nativeQuery = true)
    @Query(value = "from Player p where p.teamId=:idTeam")
    List<Player> findAllByTeamId(@Param("idTeam") int teamId);

    @Transactional
    @Query(value = "select * from player_table where player_table.id_team=:idTeam and player_table.position=:position", nativeQuery = true)
    List<Player> findAllByTeamIdAndPosition(@Param("idTeam") int teamId, @Param("position") String position);
}
