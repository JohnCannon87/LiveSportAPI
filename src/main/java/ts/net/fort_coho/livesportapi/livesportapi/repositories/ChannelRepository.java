package ts.net.fort_coho.livesportapi.livesportapi.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ts.net.fort_coho.livesportapi.livesportapi.model.Channel;

@Repository
public class ChannelRepository {

  private Map<String, Channel> channels = new HashMap<>();

  public Optional<Channel> get(String channelName) {
    return Optional.ofNullable(channels.get(channelName));
  }

  public Channel save(Channel channel) {
    channels.put(channel.getName(), channel);
    return channel;
  }

}
