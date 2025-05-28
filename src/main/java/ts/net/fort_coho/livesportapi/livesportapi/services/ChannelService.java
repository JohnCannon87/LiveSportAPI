package ts.net.fort_coho.livesportapi.livesportapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ts.net.fort_coho.livesportapi.livesportapi.model.Channel;
import ts.net.fort_coho.livesportapi.livesportapi.repositories.ChannelRepository;

@Service
public class ChannelService {

  @Autowired
  private ChannelRepository channelRepo;

  public List<Channel> convertChannelData(Elements channelData) {
    List<Channel> result = new ArrayList<>();
    ArrayList<Element> channelDataList = channelData.asList();

    for (Element channelElement : channelDataList) {
      String channelText = channelElement.ownText();
      result.add(getChannel(channelText));
    }

    return result;
  }

  private Channel getChannel(String channelName) {
    Optional<Channel> optionalTeam = channelRepo.get(channelName);

    return optionalTeam.orElseGet(() -> channelRepo.save(Channel.builder().name(channelName).build()));
  }

}
