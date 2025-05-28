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
public class Match {

  private String competition;
  private Team homeTeam;
  private Team awayTeam;
  @JsonFormat(pattern = "HH:mm")
  private LocalDateTime kickoff;
  private List<Channel> channelsList;
  private String channels;

  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append(String.format("\n%s:\n", competition));
    builder.append(String.format("%s v %s ", homeTeam.getName(), awayTeam.getName()));
    builder.append(String.format("@ %s\n", kickoff));
    builder.append(String.format("Channels: %s\n", channels));

    return builder.toString();
  }

}
