package com.World_Navigator.demo.game;

import com.World_Navigator.demo.MazeFile.Maze;
import com.World_Navigator.demo.MazeFile.MazeCreator;
import com.World_Navigator.demo.MazeFile.MazeFactory;
import com.World_Navigator.demo.MazeFile.Room;
import com.World_Navigator.demo.PersonFile.Inventory;
import com.World_Navigator.demo.PersonFile.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private List<Game> activeGames = new ArrayList<>();
  private MazeFactory mazeFactory = MazeFactory.instance();
  private final SimpMessagingTemplate simpMessagingTemplate;
  private int lastGameId = 1;

  public GameService(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  public Game createGame(Player creator) {
    Maze maze = MazeCreator.createMaze(mazeFactory);
    Game game = new Game(lastGameId++, creator, maze);
    activeGames.add(game);
    return game;
  }

  public boolean joinGame(int gameId, Player player) {
    Game game = findGame(gameId);
    if(game == null || game.isStarted())
      return false;
    game.getPlayers().add(player);
    return true;
  }

  public List<Game> getActiveGames() {
    return activeGames;
  }

  public boolean exitGame(int gameId, Player player) {
    Game game = findGame(gameId);

    if(game != null && game.getPlayers().contains(player)) {
      game.getPlayers().remove(player);
      if(game.getPlayers().isEmpty()) {
        activeGames.remove(game);
      } else {
        // TODO: distribute items and gold upon exit

      }
      return true;
    } else {
      return false;
    }
  }

  public void finishGame(Player player) {
    Game game = findGame(player);
    if(game == null)
      return;
    for(Player player1 : game.getPlayers()) {
      simpMessagingTemplate.convertAndSendToUser(player1.getIp(), "/queue/messages", "GAME END");
    }
    activeGames.remove(game);
  }

  public void startGame(Game game) {
    Maze maze = game.getMaze();
    Random rand = new Random();
    int numOfRooms = maze.getNumOfRooms();

    for(Player player : game.getPlayers()) {
      int roomNo = rand.nextInt(numOfRooms);
      Room room = (Room) maze.getRooms().get(roomNo);
      /*
        while room is exit room, then find another starting room
       */
      Inventory inventory = new Inventory();
      player.setStartRoom(room);
      int n = player.getCurrentRoom().getNumberOfPlayers();
      n++;
      player.getCurrentRoom().setNumberOfPlayers(n);
      System.out.println("number of players in the room " + n );
      player.setGold(20);
      player.setInventory(inventory);
    }
    game.setStarted(true);
  }

  public Game findGame(int gameId) {
    for(Game game : activeGames)
      if(game.getId() == gameId)
        return game;
    return null;
  }

  public Game findGame(Player player) {
    for(Game game : activeGames) {
      for(Player player1 : game.getPlayers())
        if(player.equals(player1))
          return game;
    }
    return null;
  }
}
