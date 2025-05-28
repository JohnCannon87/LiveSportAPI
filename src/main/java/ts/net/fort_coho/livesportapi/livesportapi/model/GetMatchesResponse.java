package ts.net.fort_coho.livesportapi.livesportapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMatchesResponse {

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime responseTime;
  private Map<LocalDate, List<Match>> matches;
}
