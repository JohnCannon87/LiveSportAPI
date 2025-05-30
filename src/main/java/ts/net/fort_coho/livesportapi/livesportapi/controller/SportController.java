package ts.net.fort_coho.livesportapi.livesportapi.controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters.ChannelExclusionMatchFilterFactory;
import ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters.CompetitionExclusionMatchFilterFactory;
import ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters.DateRangeMatchFilterFactory;
import ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters.MatchFilter;
import ts.net.fort_coho.livesportapi.livesportapi.model.GetMatchesResponse;
import ts.net.fort_coho.livesportapi.livesportapi.model.Match;
import ts.net.fort_coho.livesportapi.livesportapi.model.MatchGroup;
import ts.net.fort_coho.livesportapi.livesportapi.services.MatchService;

@RestController
@RequestMapping("/api")
public class SportController {

  @Autowired
  private MatchService matchService;

  @GetMapping("/matches")
  public GetMatchesResponse getMatches(
      @RequestParam(name = "excludeComps", required = false) String competitionsToExclude,
      @RequestParam(name = "daysInFuture", required = false, defaultValue = "0") int daysInFuture,
      @RequestParam(name = "excludeChannels", required = false) String channelsToExclude) {

    List<Match> matches = matchService.getAll();

    List<MatchFilter> filters = List.of(
        DateRangeMatchFilterFactory.of(daysInFuture),
        ChannelExclusionMatchFilterFactory.of(channelsToExclude),
        CompetitionExclusionMatchFilterFactory.of(competitionsToExclude)
    );

    List<Match> filteredMatches = matches.stream()
        .filter(match -> filters.stream().allMatch(filter -> filter.test(match)))
        .sorted(Comparator.comparing(Match::getKickoff))
        .collect(Collectors.toList());

    // Group by match day and create MatchGroup instances, sorted by date
    List<MatchGroup> matchGroups = filteredMatches.stream()
        .collect(Collectors.groupingBy(match -> match.getKickoff().toLocalDate()))
        .entrySet()
        .stream()
        .sorted(Comparator.comparing(e -> e.getKey()))
        .map(entry -> MatchGroup.builder()
            .matchDay(entry.getKey())
            .matches(entry.getValue())
            .build())
        .collect(Collectors.toList());

    return GetMatchesResponse.builder()
        .responseTime(LocalDateTime.now())
        .matchGroups(matchGroups)
        .build();
  }

}
