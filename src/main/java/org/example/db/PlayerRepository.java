package org.example.db;

import org.example.db.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Transactional
    int countByTeam_TeamId(int teamId);

    @Transactional
    List<Player> findAllByTeam_TeamName(String teamName);

    @Transactional
    List<Player> findAllByTeam_TeamNameAndPosition(String teamName, String position);
}
