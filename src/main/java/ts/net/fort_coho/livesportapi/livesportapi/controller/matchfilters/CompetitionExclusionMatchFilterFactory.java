package ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters;

import org.springframework.util.StringUtils;

public class CompetitionExclusionMatchFilterFactory {

  public static MatchFilter of(String exclusionRegex) {
    if (!StringUtils.hasText(exclusionRegex)) {
      return match -> true; // no filtering
    }

    return match -> !match.getCompetition().matches(exclusionRegex);
  }
}
