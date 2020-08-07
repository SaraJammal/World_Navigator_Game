package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.ItemsFile.Door;
import com.World_Navigator.demo.ItemsFile.Item;
import com.World_Navigator.demo.PersonFile.Player;

import java.io.IOException;

public class UseDoorKey implements Command{

    private Player player;

    public UseDoorKey (Player player){
        this.player = player;
    }

    @Override
    public String execute() throws IOException {
        Item item = this.player.getCurrentRoom().getRoomItem(this.player.getCurrentDirection());
        String s;
        Door door = (Door) item;
        if (this.player.getInventory().hasItem(door.getKey().toString())) {
            if (door.isOpen()) door.setDoorToOpen(door);
            else door.setDoorToLocked();
            return "You used the key";
        }
        s = "You dont have a key to use it for this item";
        return s;
    }


}
