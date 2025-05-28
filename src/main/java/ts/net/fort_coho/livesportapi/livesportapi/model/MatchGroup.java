package ts.net.fort_coho.livesportapi.livesportapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchGroup {

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate matchDay;
  private List<Match> matches;

}
