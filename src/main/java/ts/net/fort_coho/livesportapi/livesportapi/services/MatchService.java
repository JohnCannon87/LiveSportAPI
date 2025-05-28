package ts.net.fort_coho.livesportapi.livesportapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ts.net.fort_coho.livesportapi.livesportapi.model.Channel;
import ts.net.fort_coho.livesportapi.livesportapi.model.Competition;
import ts.net.fort_coho.livesportapi.livesportapi.model.Match;
import ts.net.fort_coho.livesportapi.livesportapi.model.Team;
import ts.net.fort_coho.livesportapi.livesportapi.repositories.MatchRepository;

@Service
public class MatchService {

  @Autowired
  private MatchRepository matchRepo;

  public List<Match> getAll() {
    return matchRepo.getAll();
  }

  public void createAndSaveMatch(Competition competition, LocalDateTime kickoff, Pair<Team, Team> teams,
      List<Channel> channels) {
    Match match = Match.builder()
        .competition(competition.getName())
        .homeTeam(teams.getValue0())
        .awayTeam(teams.getValue1())
        .kickoff(kickoff)
        .channels(channels)
        .build();

    competition.addMatch(match);
    matchRepo.save(match);
  }

}
