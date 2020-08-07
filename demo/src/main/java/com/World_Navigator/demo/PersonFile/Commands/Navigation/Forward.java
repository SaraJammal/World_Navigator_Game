package com.World_Navigator.demo.PersonFile.Commands.Navigation;

import com.World_Navigator.demo.ItemsFile.Door;
import com.World_Navigator.demo.PersonFile.Commands.Command;
import com.World_Navigator.demo.PersonFile.Player;
import com.World_Navigator.demo.game.GameService;

import static java.lang.System.exit;

public class Forward implements Command {

    private Player player;
    public Forward(Player player){
        this.player = player;
    }

  @Override
  public String execute() {
    String s;
    if (player
        .getCurrentRoom()
        .getRoomItem(player.getCurrentDirection())
        .toString()
        .equals("Door")) {
      Door door = (Door) player.getCurrentRoom().getRoomItem(player.getCurrentDirection());
//          Door backDoor = (Door) player.getCurrentRoom().getRoomItem(player.getCurrentDirection().opposite(player.getCurrentDirection()));
        if (door.isOpen()) {
              s = "Door is OPEN\n";
//            backDoor.setDoorToOpen(backDoor);
            if (door.isExit()) {
              s = s + "You have won!";
              return s;
            }
            s = s + "Player moved to " + door.getRoomTo().toString();
            player.changeRoom(door.getRoomTo(), player.getCurrentDirection());
            return s;
          } else {
            s = "Door is closed, can't move forward";
            return s;
          }
        }
        s = "No door in front of you, can't move forward";
        return s;
    }
}
