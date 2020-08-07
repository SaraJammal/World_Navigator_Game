package com.World_Navigator.demo.PersonFile.Commands.Navigation;

import com.World_Navigator.demo.ItemsFile.Door;
import com.World_Navigator.demo.PersonFile.Commands.Command;
import com.World_Navigator.demo.PersonFile.Player;

public class Backward implements Command {

    private Player player;
    public Backward(Player player){
        this.player=player;
    }

    @Override
    public String execute() {
        String s;
        if (player
                .getCurrentRoom()
                .getRoomItem(player.getCurrentDirection().opposite(player.getCurrentDirection()))
                .toString()
                .equals("Door")) {
            Door door =
                    (Door)
                            player
                                    .getCurrentRoom()
                                    .getRoomItem(player.getCurrentDirection().opposite(player.getCurrentDirection()));
            if (door != null) {
                if (door.isOpen()) {
                    s = "Player moved to " + door.getRoomTo().toString();
                    player.changeRoom(door.getRoomFrom(), player.getCurrentDirection());
                    return s;
                } else {
                    s = "Door is closed, can't move backward";
                    return s;
                }
            }
        }
        s = "No door behind of you, can't move backward";
        return s;
    }


}
