package com.World_Navigator.demo.game;

import com.World_Navigator.demo.PersonFile.Player;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class IPService {

  private final Map<String, Player> playerMap = new HashMap<>();
  private final GameService gameService;

  public IPService(GameService gameService) {
    this.gameService = gameService;
  }

  public boolean ipExists(String ip) {
    return playerMap.containsKey(ip);
  }

  public Player getPlayer(String ip) {
    if(ipExists(ip))
      return playerMap.get(ip);
    return null;
  }

  public void removePlayer(String ip) {
    playerMap.remove(ip);
  }

  public Player createPlayer(String username, String ip) {
    System.out.println("Username = " + username + ", IP = " + ip);
    /*if(playerMap.containsKey(ip)) {
      Player player = playerMap.get(ip);
      Game game = gameService.findGame(player);
      if(game != null)
        gameService.exitGame(game.getId(), player);
    }*/

    Player player = new Player(username, ip);
    playerMap.put(ip, player);
    return player;
  }
}
