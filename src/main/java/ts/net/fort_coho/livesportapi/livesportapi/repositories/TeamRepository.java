package ts.net.fort_coho.livesportapi.livesportapi.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ts.net.fort_coho.livesportapi.livesportapi.model.Team;

@Repository
public class TeamRepository {

  private Map<String, Team> teams = new HashMap<>();

  public Optional<Team> get(String teamName) {
    return Optional.ofNullable(teams.get(teamName));
  }

  public Team save(Team team) {
    teams.put(team.getName(), team);
    return team;
  }

}
