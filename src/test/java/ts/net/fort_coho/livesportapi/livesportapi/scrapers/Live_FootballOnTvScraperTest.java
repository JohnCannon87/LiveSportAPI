package ts.net.fort_coho.livesportapi.livesportapi.scrapers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Live_FootballOnTvScraperTest {

  @Autowired
  private Live_FootballOnTVScraper underTest;

  @Test
  void testImplementation() {
    underTest.execute();

  }

}
