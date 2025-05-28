package ts.net.fort_coho.livesportapi.livesportapi.scrapers;

import org.springframework.beans.factory.annotation.Autowired;

import ts.net.fort_coho.livesportapi.livesportapi.services.ChannelService;
import ts.net.fort_coho.livesportapi.livesportapi.services.CompetitionService;
import ts.net.fort_coho.livesportapi.livesportapi.services.MatchService;
import ts.net.fort_coho.livesportapi.livesportapi.services.SportService;
import ts.net.fort_coho.livesportapi.livesportapi.services.TeamService;
import ts.net.fort_coho.livesportapi.livesportapi.services.TimeService;

public abstract class AbstractScraper {

  @Autowired
  protected SportService sportService;

  @Autowired
  protected CompetitionService competitionService;

  @Autowired
  protected MatchService matchService;

  @Autowired
  protected TeamService teamService;

  @Autowired
  protected ChannelService channelService;

  @Autowired
  protected TimeService timeService;

  abstract void scrapeSite();

  abstract String getSport();

}
