package com.World_Navigator.demo.game;

import com.World_Navigator.demo.MazeFile.Maze;
import com.World_Navigator.demo.PersonFile.Player;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class Game {

  private int id;
  private Player creator;
  private Set<Player> players = new HashSet<>();
  private Maze maze;
  private boolean started;

  public Game(int id, Player creator, Maze maze) {
    this.id = id;
    this.creator = creator;
    this.maze = maze;
    this.players.add(creator);
    this.started = false;
  }
}
