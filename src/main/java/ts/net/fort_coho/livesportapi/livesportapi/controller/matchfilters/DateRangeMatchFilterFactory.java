package ts.net.fort_coho.livesportapi.livesportapi.controller.matchfilters;

import java.time.LocalDate;

public class DateRangeMatchFilterFactory {

  public static MatchFilter of(int daysInFuture) {
    LocalDate today = LocalDate.now();
    LocalDate endDate = today.plusDays(daysInFuture);

    return match -> {
      LocalDate matchDate = match.getKickoff().toLocalDate();
      return !matchDate.isBefore(today) && !matchDate.isAfter(endDate);
    };
  }
}
