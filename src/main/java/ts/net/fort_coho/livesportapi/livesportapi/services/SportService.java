package ts.net.fort_coho.livesportapi.livesportapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ts.net.fort_coho.livesportapi.livesportapi.model.Sport;
import ts.net.fort_coho.livesportapi.livesportapi.repositories.SportRepository;

@Service
public class SportService {

  @Autowired
  private SportRepository sportRepo;

  public List<Sport> getAll() {
    return sportRepo.getAll();
  }

  public Sport getSport(String sportName) {
    Optional<Sport> optionalSport = sportRepo.get(sportName);

    return optionalSport.orElseGet(() -> sportRepo.save(Sport.builder().name(sportName).build()));

  }
}
