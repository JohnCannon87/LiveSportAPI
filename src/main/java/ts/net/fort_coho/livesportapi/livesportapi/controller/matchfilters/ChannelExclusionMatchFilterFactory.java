package ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class ChannelExclusionMatchFilterFactory {

  public static MatchFilter of(String exclusionListCsv) {
    if (!StringUtils.hasText(exclusionListCsv)) {
      return match -> true; // no filtering
    }

    Set<String> excludedChannels = Arrays.stream(exclusionListCsv.split(","))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .map(String::toLowerCase)  // case-insensitive: store as lower case
        .collect(Collectors.toSet());

    return match -> {
      // Check if ALL channels are excluded (i.e. all in excludedChannels set)
      return match.getChannels()
          .stream()
          .map(channel -> channel.getName().toLowerCase())  // case-insensitive compare
          .allMatch(excludedChannels::contains) == false; // keep match only if NOT all are excluded
    };
  }
}
