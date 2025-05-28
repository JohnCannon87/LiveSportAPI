package ts.net.fort_coho.livesportapi.livesportapi.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

  public LocalDateTime convertDateAndTimeData(String dateString, Elements timeData) {
    String fullDateTimeString = dateString + " " + timeData.asList().get(0).ownText();

    if (fullDateTimeString.contains("TBC")) {
      return convertDateTimeString(dateString + " 00:01");
    }

    return convertDateTimeString(fullDateTimeString);
  }

  public LocalDateTime convertDateTimeString(String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy HH:mm", Locale.ENGLISH);
    String cleanedDateString = dateTimeString.replaceAll("(\\d+)(st|nd|rd|th)", "$1");

    LocalDateTime localDateTime = LocalDateTime.parse(cleanedDateString, formatter);

    return localDateTime;
  }

}
