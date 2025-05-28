package ts.net.fort_coho.livesportapi.livesportapi.services;

import java.util.List;
import java.util.Optional;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ts.net.fort_coho.livesportapi.livesportapi.model.Competition;
import ts.net.fort_coho.livesportapi.livesportapi.model.Match;
import ts.net.fort_coho.livesportapi.livesportapi.model.Sport;
import ts.net.fort_coho.livesportapi.livesportapi.repositories.CompetitionRepository;

@Service
public class CompetitionService {

  @Autowired
  private CompetitionRepository competitionRepo;

  public Competition convertCompetitionData(Elements competitionDataList, Sport sport) {
    String competitionName = competitionDataList.asList().get(0).ownText();

    Optional<Competition> competitionOptional = competitionRepo.get(competitionName);

    if (competitionOptional.isEmpty()) {
      Competition competition = Competition.builder().name(competitionName).build();
      sport.addCompetition(competition);
      competitionRepo.save(competition);
      return competition;
    }

    return competitionOptional.get();
  }

  public void removeAllMatchesFromAllCompetitions(List<Match> matches) {
    for (Competition competition : competitionRepo.getAll()) {
      competition.getMatches().removeAll(matches);
    }
  }

}
