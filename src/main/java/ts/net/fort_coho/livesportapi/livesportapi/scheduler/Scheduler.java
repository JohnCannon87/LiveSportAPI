package ts.net.fort_coho.livesportapi.livesportapi.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import ts.net.fort_coho.livesportapi.livesportapi.scrapers.Scraper;

@Service
public class Scheduler {

  @Autowired
  private List<Scraper> scrapers;

  @PostConstruct
  public void onStartup() {
    scrapeAll();
  }

  @Scheduled(cron = "14 4 * * *")
  public void scheduled() {
    scrapeAll();
  }

  private void scrapeAll() {
    for (Scraper scraper : scrapers) {
      scraper.execute();
    }
  }
}
