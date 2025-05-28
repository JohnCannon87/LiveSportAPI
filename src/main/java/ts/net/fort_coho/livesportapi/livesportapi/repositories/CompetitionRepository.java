package ts.net.fort_coho.livesportapi.livesportapi.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ts.net.fort_coho.livesportapi.livesportapi.model.Competition;

@Repository
public class CompetitionRepository {

  private Map<String, Competition> competitions = new HashMap<>();

  public List<Competition> getAll() {
    return List.copyOf(competitions.values());
  }

  public Optional<Competition> get(String competitionName) {
    return Optional.ofNullable(competitions.get(competitionName));
  }

  public Competition save(Competition competition) {
    competitions.put(competition.getName(), competition);

    return competition;
  }

}
