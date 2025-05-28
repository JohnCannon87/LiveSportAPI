package ts.net.fort_coho.livesportapi.livesportapi.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ts.net.fort_coho.livesportapi.livesportapi.model.Sport;

@Repository
public class SportRepository {

  private Map<String, Sport> sports = new HashMap<>();

  public List<Sport> getAll() {
    return List.copyOf(sports.values());
  }

  public Optional<Sport> get(String sportName) {
    return Optional.ofNullable(sports.get(sportName));
  }

  public Sport save(Sport sport) {
    sports.put(sport.getName(), sport);
    return sport;
  }

}
