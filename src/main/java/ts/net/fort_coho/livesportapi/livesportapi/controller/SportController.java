package ts.net.fort_coho.livesportapi.livesportapi.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters.ChannelExclusionMatchFilterFactory;
import ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters.CompetitionExclusionMatchFilterFactory;
import ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters.DateRangeMatchFilterFactory;
import ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters.MatchFilter;
import ts.net.fort_coho.livesportapi.livesportapi.model.Match;
import ts.net.fort_coho.livesportapi.livesportapi.services.MatchService;

@RestController
@RequestMapping("/api")
public class SportController {

  @Autowired
  private MatchService matchService;

  @GetMapping("/matches")
  public List<Match> getMatches(
      @RequestParam(name = "excludeComps", required = false) String competitionsToExclude,
      @RequestParam(name = "daysInFuture", required = false, defaultValue = "0") int daysInFuture,
      @RequestParam(name = "excludeChannels", required = false) String channelsToExclude) {

    List<Match> result = new ArrayList<>();
    List<Match> matches = matchService.getAll();

    List<MatchFilter> filters = List.of(
        DateRangeMatchFilterFactory.of(daysInFuture),
        ChannelExclusionMatchFilterFactory.of(channelsToExclude),
        CompetitionExclusionMatchFilterFactory.of(competitionsToExclude)
    );

    for (Match match : matches) {
      boolean allMatch = true;
      for (MatchFilter filter : filters) {
        if (!filter.test(match)) {
          allMatch = false;
          break;
        }
      }

      if (allMatch) {
        result.add(match);
      }
    }

    result.sort(Comparator.comparing(Match::getKickoff));

    return result;
  }

}
