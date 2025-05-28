package ts.net.fort_coho.livesportapi.livesportapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ts.net.fort_coho.livesportapi.livesportapi.model.Match;

@Service
public class CleanupService {

  @Autowired
  private MatchService matchService;

  @Autowired
  private CompetitionService competitionService;

  public void cleanupBeforeToday() {
    List<Match> allMatches = matchService.getAll();
    List<Match> matchesToRemove = new ArrayList<>();
    for (Match match : allMatches) {
      if (match.getKickoff().toLocalDate().isBefore(LocalDate.now())) {
        matchesToRemove.add(match);
      }
    }
    matchService.removeMatches(matchesToRemove);
    competitionService.removeAllMatchesFromAllCompetitions(matchesToRemove);
  }

}
