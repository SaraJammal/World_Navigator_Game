package com.World_Navigator.demo.PersonFile.Commands.Navigation;

import com.World_Navigator.demo.MazeFile.Directions;
import com.World_Navigator.demo.PersonFile.Commands.Command;
import com.World_Navigator.demo.PersonFile.Player;
import java.io.IOException;

public class Right implements Command {

  private Player player;
  public Right(Player player){
    this.player = player;
  }
  @Override
  public String execute() throws IOException {
    Directions d = player.getCurrentDirection().nextRight(player.getCurrentDirection());
    if(d == null)
      return "false direction";
    if(d.equals(Directions.NORTH)){
      return "You are facing north now";
    }else{
      if(d.equals(Directions.EAST)){
        return "You are facing east now";
      }else{
        if(d.equals(Directions.SOUTH)){
          return "You are facing south now";
        }
        else {
          return "You are facing west now";
        }
      }
    }
  }
}
