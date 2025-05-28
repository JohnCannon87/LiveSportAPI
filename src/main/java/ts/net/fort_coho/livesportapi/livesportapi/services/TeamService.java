package ts.net.fort_coho.livesportapi.livesportapi.services;

import java.util.Optional;

import org.javatuples.Pair;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ts.net.fort_coho.livesportapi.livesportapi.model.Sport;
import ts.net.fort_coho.livesportapi.livesportapi.model.Team;
import ts.net.fort_coho.livesportapi.livesportapi.repositories.TeamRepository;

@Slf4j
@Service
public class TeamService {

  @Autowired
  private TeamRepository teamRepo;

  public Pair<Team, Team> convertTeamsData(Elements teamsData, Sport sport) {
    String versusString = teamsData.asList().get(0).ownText();
    if (versusString.equalsIgnoreCase("TBC")) {
      return new Pair<Team, Team>(getTeam("TBC"), getTeam("TBC"));
    }
    String[] splitString = versusString.split(" v ");
    try {
      Team homeTeam;
      Team awayTeam;
      switch (sport.getName()) {
        case "NFL":
          awayTeam = getTeam(splitString[0]);
          homeTeam = getTeam(splitString[1]);
          break;
        case "Football":
        default:
          homeTeam = getTeam(splitString[0]);
          awayTeam = getTeam(splitString[1]);
          break;
      }
      return new Pair<Team, Team>(homeTeam, awayTeam);
    } catch (ArrayIndexOutOfBoundsException e) {
      log.error("Array Index out of bounds for string: {}", versusString);
      return null;
    }
  }

  private Team getTeam(String teamName) {
    Optional<Team> optionalTeam = teamRepo.get(teamName);

    return optionalTeam.orElse(teamRepo.save(Team.builder().name(teamName).build()));
  }

}
