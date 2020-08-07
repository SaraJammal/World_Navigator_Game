package com.World_Navigator.demo.controllers;

import com.World_Navigator.demo.PersonFile.Player;
import com.World_Navigator.demo.game.Game;
import com.World_Navigator.demo.game.GameService;
import com.World_Navigator.demo.game.IPService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  private final GameService gameService;
  private final SimpMessagingTemplate simpMessagingTemplate;
  private final IPService ipService;

  @Autowired
  MainController(GameService gameService,
      SimpMessagingTemplate simpMessagingTemplate,
      IPService ipService) {
    this.gameService = gameService;
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.ipService = ipService;
  }

  @RequestMapping("/")
  public String index(Model model) {
    // IF HES IN A GAME REDIRECT TO ROOM/GAME
    model.addAttribute("games", gameService.getActiveGames());
    return "index";
  }

  @RequestMapping("createGame")
  public String createGame(Model model, @RequestParam String username, HttpServletRequest request) {

    Player player = ipService.createPlayer(username, request.getRemoteAddr());

    Game game = gameService.createGame(player);

    return "redirect:/room";
  }

  @RequestMapping("room")
  public String viewRoom(HttpServletRequest request, Model model) {
    String ip = request.getRemoteAddr();
    Player player = ipService.getPlayer(ip);
    if(player == null)
      return "redirect:/";

    Game game = gameService.findGame(player);
    if(game == null)
      return "redirect:/";

    model.addAttribute("game", game);
    model.addAttribute("player", player);

    return "viewRoom";
  }

  @RequestMapping("leave")
  public String leaveGame(HttpServletRequest request) {
    String ip = request.getRemoteAddr();
    Player player = ipService.getPlayer(ip);
    if(player == null)
      return "redirect:/";
    Game game = gameService.findGame(player);
    if(game == null)
      return "redirect:/";
    gameService.exitGame(game.getId(), player);
    return "redirect:/";
  }

  @RequestMapping("joinGame/{gameId}")
  public String joinGame(@PathVariable int gameId, @RequestParam String username, HttpServletRequest request) {
    Player player = ipService.createPlayer(username, request.getRemoteAddr());

    if(gameService.joinGame(gameId, player))
      return "redirect:/room";
    return "redirect:/";
  }


  @RequestMapping("startGame")
  private String startGame(HttpServletRequest request) {
    String ip = request.getRemoteAddr();
    Player player = ipService.getPlayer(ip);
    if(player == null)
      return "redirect:/";
    Game game = gameService.findGame(player);
    if(game == null)
      return "redirect:/";
    if(game.isStarted())
      return "redirect:/play";
    if(!game.getCreator().equals(player))
      return "redirect:/room";

    gameService.startGame(game);
    for(Player player1 : game.getPlayers())
      simpMessagingTemplate.convertAndSendToUser(player1.getIp(), "/queue/messages", "GAME START");
    return "redirect:/play";
  }

  @RequestMapping("play")
  public String play(HttpServletRequest request) {
    String ip = request.getRemoteAddr();
    Player player = ipService.getPlayer(ip);
    if(player == null)
      return "redirect:/";
    Game game = gameService.findGame(player);
    if(game == null || !game.isStarted())
      return "redirect:/room";
    return "play";
  }
}
