package ts.net.fort_coho.livesportapi.livesportapi.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ts.net.fort_coho.livesportapi.livesportapi.model.Match;

@Repository
public class MatchRepository {

  private List<Match> matches = new ArrayList<>();

  public List<Match> getAll() {
    return matches;
  }

  public void save(Match match) {
    if (!matches.contains(match)) {
      matches.add(match);
    }
  }

}
