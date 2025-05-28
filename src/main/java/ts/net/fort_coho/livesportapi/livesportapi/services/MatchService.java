package ts.net.fort_coho.livesportapi.livesportapi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

  public void removeMatches(List<Match> matches) {
    matchRepo.removeMatches(matches);
  }

  public void createAndSaveMatch(Competition competition, LocalDateTime kickoff, Pair<Team, Team> teams,
      List<Channel> channels, String sourceUrl) {
    Match match = Match.builder()
        .competition(competition.getName())
        .homeTeam(teams.getValue0())
        .awayTeam(teams.getValue1())
        .kickoff(kickoff)
        .channelsList(channels)
        .channels(convertChannelsToCSV(channels))
        .sourceUrl(sourceUrl)
        .build();

    competition.addMatch(match);
    matchRepo.save(match);
  }

  private String convertChannelsToCSV(List<Channel> channels) {
    if (channels == null || channels.isEmpty()) {
      return "";
    }
    return channels.stream()
        .map(Channel::getName)
        .collect(Collectors.joining(", "));
  }

}
