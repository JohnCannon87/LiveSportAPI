package ts.net.fort_coho.livesportapi.livesportapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ts.net.fort_coho.livesportapi.livesportapi.TestData;

class TimeServiceTest {

  TimeService underTest = new TimeService();

  @Test
  public void testHtmlConversion() {
    // Given
    int year = 2025;
    int month = 3;
    int day = 26;
    int hour = 14;
    int minute = 32;

    String dateString = "Wednesday 26th March 2025";
    Document document = Jsoup.parse(TestData.fixtureData);
    Elements timeElements = document.select("div.fixture__time");

    // When
    LocalDateTime result = underTest.convertDateAndTimeData(dateString, timeElements);

    // Then
    assertEquals(year, result.getYear());
    assertEquals(month, result.getMonthValue());
    assertEquals(day, result.getDayOfMonth());
    assertEquals(hour, result.getHour());
    assertEquals(minute, result.getMinute());
  }

  @ParameterizedTest
  @CsvSource({
               "'Saturday 31st May 2025 20:00', 2025, 5, 31, 20, 0",
               "'Sunday 1st June 2025 09:30', 2025, 6, 1, 9, 30",
               "'Monday 2nd June 2025 14:45', 2025, 6, 2, 14, 45",
               "'Tuesday 3rd June 2025 07:15', 2025, 6, 3, 7, 15",
               "'Wednesday 4th June 2025 23:59', 2025, 6, 4, 23, 59"
  })
  public void testParseToLocalDateTime_ValidInputs(String input, int year, int month, int day, int hour, int minute) {
    // When
    LocalDateTime result = underTest.convertDateTimeString(input);

    // Then
    assertEquals(year, result.getYear());
    assertEquals(month, result.getMonthValue());
    assertEquals(day, result.getDayOfMonth());
    assertEquals(hour, result.getHour());
    assertEquals(minute, result.getMinute());
  }

  @ParameterizedTest
  @CsvSource({
               "''",
               "'Invalid Date String'",
               "'Friday 32nd June 2025 25:61'",
               "'Monday 3rd June 2025 07:15', 2025, 6, 3, 7, 15",
  })
  public void testParseToLocalDateTime_InvalidInputs(String input) {
    // Then
    assertThrows(Exception.class, () -> {
      underTest.convertDateTimeString(input);
    });
  }

}
