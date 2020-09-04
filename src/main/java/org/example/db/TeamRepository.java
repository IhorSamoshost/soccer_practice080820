package org.example.db;

import org.example.db.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Transactional
    Team findByTeamName(String teamName);

}
