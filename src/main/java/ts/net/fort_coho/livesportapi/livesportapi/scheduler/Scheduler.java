package ts.net.fort_coho.livesportapi.livesportapi.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import ts.net.fort_coho.livesportapi.livesportapi.scrapers.Scraper;
import ts.net.fort_coho.livesportapi.livesportapi.services.CleanupService;

@Service
public class Scheduler {

  @Autowired
  private List<Scraper> scrapers;

  @Autowired
  private CleanupService cleanupService;

  @PostConstruct
  public void onStartup() {
    scrapeAll();
  }

  @Scheduled(cron = "14 6 * * *")
  public void scheduled() {
    scrapeAll();
    cleanupService.cleanupBeforeToday();
  }

  private void scrapeAll() {
    for (Scraper scraper : scrapers) {
      scraper.execute();
    }
  }
}
