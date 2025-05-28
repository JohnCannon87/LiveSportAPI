package ts.net.fort_coho.livesportapi.livesportapi.model;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sport {

  private String name;
  @Builder.Default
  private Map<String, Competition> competitions = new HashMap<>();

  public Sport addCompetition(Competition competition) {
    competitions.put(competition.getName(), competition);
    return this;
  }

}
