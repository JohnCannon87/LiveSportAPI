package ts.net.fort_coho.livesportapi.livesportapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

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
  private List<MatchGroup> matchGroups;
}
