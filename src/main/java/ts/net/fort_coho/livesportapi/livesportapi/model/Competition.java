package ts.net.fort_coho.livesportapi.livesportapi.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {

  private String name;
  private List<Match> matches;

  public Competition addMatch(Match match) {
    if (matches == null) {
      matches = new ArrayList<>();
    }
    matches.add(match);
    return this;
  }

}
