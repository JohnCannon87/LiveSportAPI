package ts.net.fort_coho.livesportapi.livesportapi.scrapers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import ts.net.fort_coho.livesportapi.livesportapi.model.Channel;
import ts.net.fort_coho.livesportapi.livesportapi.model.Competition;
import ts.net.fort_coho.livesportapi.livesportapi.model.FixtureDateAndData;
import ts.net.fort_coho.livesportapi.livesportapi.model.FixtureDateAndData.FixtureDateAndDataBuilder;
import ts.net.fort_coho.livesportapi.livesportapi.model.Sport;
import ts.net.fort_coho.livesportapi.livesportapi.model.Team;

/*
 * Scraper for https://www.live-footballontv.com/popular-competitions.html, specifically for popular competitions to
 * ease filtering of Belgian 3rd division etc.
 */
@Slf4j
@Service
public class Live_FootballOnTVScraper extends AbstractScraper {

  @Value("${scrapers.livefootballontv.url}")
  private String liveFootballOnTvUrl;

  @Value("${scrapers.livefootballontv.shouldSkipDelay:true}")
  private boolean shouldSkipDelay;

  @PostConstruct
  public void onStartup() {
    scrapeSite();
  }

  @Scheduled(cron = "14 4 * * *")
  public void scheduled() {
    scrapeSite();
  }

  public void scrapeSite() {
    log.info(String.format("Scraping %s", liveFootballOnTvUrl));
    try {
      Document mainPage = Jsoup.connect(liveFootballOnTvUrl).get();

      List<Element> fixtureGroups = mainPage.select("div.fixture-group");

      List<FixtureDateAndData> fixturesData = new ArrayList<>();
      for (Element fixtureGroup : fixtureGroups) {
        Element fixtureDate = fixtureGroup.selectFirst("div.fixture-date");
        FixtureDateAndDataBuilder builder = FixtureDateAndData.builder().fixtureDate(fixtureDate);

        Elements siblingElements = fixtureDate.siblingElements();
        for (Element sibling : siblingElements) {
          String className = sibling.className();
          switch (className) {
            case "fixture-date":
              fixturesData.add(builder.build());
              builder = FixtureDateAndData.builder().fixtureDate(sibling);
              break;
            case "fixture":
              builder.fixture(sibling);
              break;
            default:
              break;
          }
        }
        fixturesData.add(builder.build());
      }

      convertFixturesDataToModel(fixturesData, liveFootballOnTvUrl);

    } catch (Exception e) {
      log.error("Error when parsing Live-FootballOnTv", e);
    }
  }

  private void convertFixturesDataToModel(List<FixtureDateAndData> fixturesData, String sourceUrl) {
    Sport sport = sportService.getSport(getSport());

    for (FixtureDateAndData fixtureDateAndData : fixturesData) {
      String dateString = fixtureDateAndData.getFixtureDate().ownText();
      List<Element> fixtures = fixtureDateAndData.getFixtures();
      for (Element fixtureData : fixtures) {
        Elements timeData = fixtureData.select("div.fixture__time");
        Elements teamsData = fixtureData.select("div.fixture__teams");
        Elements competitionData = fixtureData.select("div.fixture__competition");
        Elements channelData = fixtureData.select("div.fixture__channel").select("span.channel-pill");

        Competition competition = competitionService.convertCompetitionData(competitionData, sport);

        LocalDateTime kickoff = timeService.convertDateAndTimeData(dateString, timeData);
        Pair<Team, Team> teams = teamService.convertTeamsData(teamsData, sport);
        List<Channel> channels = channelService.convertChannelData(channelData);
        if (teams != null) {
          matchService.createAndSaveMatch(competition, kickoff, teams, channels, sourceUrl);
        }
      }
    }
  }

  @Override
  public String getSport() {
    return "Football";
  }

}
