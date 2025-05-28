package ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters;

import ts.net.fort_coho.livesportapi.livesportapi.model.Match;

@FunctionalInterface
public interface MatchFilter {
  boolean test(Match match);
}
