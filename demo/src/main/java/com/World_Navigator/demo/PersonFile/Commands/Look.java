package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.PersonFile.Player;

public class Look implements Command {
    private Player player;
    public Look(Player player){
        System.out.println("inside look constructor");

        this.player = player;
    }

    @Override
    public String execute() {
        if (!player.getCurrentRoom().isDark()) {
            return player.getCurrentRoom().getRoomItem(player.getCurrentDirection()).setDescription();
        } else {
            if (player.getCurrentRoom().isHasLight()) {
                player.pSwitchLight();
                return player.getCurrentRoom().getRoomItem(player.getCurrentDirection()).setDescription();
            } else {
                return "It's Dark!";
            }
        }
    }
}
