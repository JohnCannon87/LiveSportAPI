package ts.net.fort_coho.livesportapi.livesportapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ts.net.fort_coho.livesportapi.livesportapi.model.Competition;
import ts.net.fort_coho.livesportapi.livesportapi.model.Sport;
import ts.net.fort_coho.livesportapi.livesportapi.services.SportService;

@RestController
@RequestMapping("/api")
public class SportController {

  @Autowired
  private SportService sportService;

  @GetMapping("/{sportName}")
  public List<Competition> getCompetitions(@PathVariable("sportName") String sportName,
      @RequestParam(name = "excludeComps", required = false) String competitionsToExclude) {
    List<Competition> result = new ArrayList<>();
    Sport sport = sportService.getSport(sportName);
    Map<String, Competition> competitions = sport.getCompetitions();

    if (StringUtils.hasText(competitionsToExclude)) {
      competitions.values()
          .stream()
          .filter(c -> !c.getName().matches(competitionsToExclude))  // Exclude matching names
          .forEach(result::add);
    } else {
      result.addAll(competitions.values());
    }

    return result;
  }

}
