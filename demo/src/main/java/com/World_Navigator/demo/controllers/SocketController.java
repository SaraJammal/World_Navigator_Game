package com.World_Navigator.demo.controllers;

import com.World_Navigator.demo.PersonFile.Player;
import com.World_Navigator.demo.game.Game;
import com.World_Navigator.demo.game.GameService;
import com.World_Navigator.demo.game.IPService;
import java.io.IOException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

  private final SimpMessagingTemplate simpMessagingTemplate;
  private final GameService gameService;
  private final IPService iPService;

  public SocketController(SimpMessagingTemplate simpMessagingTemplate,
      GameService gameService, IPService iPService) {
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.gameService = gameService;
    this.iPService = iPService;
  }

  @MessageMapping("/web-socket")
  public void gameCommand(String msg, SimpMessageHeaderAccessor ha) throws IOException {
    String ip = ha.getSessionAttributes().get("ip").toString();

    if(iPService.ipExists(ip)) {
      Player player = iPService.getPlayer(ip);
      if(msg.equals("quit")) {

      } else if(msg.equals("restart")) {

      }
      String output = player.runCommand(msg);
      if(player.isWon()) {
        gameService.finishGame(player);
      }
      if(player.getCurrentRoom().getNumberOfPlayers()>1){
        Game game = gameService.findGame(player);
        output = player.runCommand("fight");
      }
      simpMessagingTemplate.convertAndSendToUser(ip, "/queue/messages", output);
    }
  }
}
