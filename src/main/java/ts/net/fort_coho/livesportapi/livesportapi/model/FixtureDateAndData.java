package ts.net.fort_coho.livesportapi.livesportapi.model;

import java.util.List;

import org.jsoup.nodes.Element;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixtureDateAndData {

  Element fixtureDate;
  @Singular
  List<Element> fixtures;

}
