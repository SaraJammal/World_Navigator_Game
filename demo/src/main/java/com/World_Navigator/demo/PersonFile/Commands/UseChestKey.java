package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.ItemsFile.Chest;
import com.World_Navigator.demo.ItemsFile.Item;
import com.World_Navigator.demo.PersonFile.Player;

import java.io.IOException;

public class UseChestKey implements Command{

    private Player player;

    public UseChestKey(Player player){
        this.player = player;
    }

    @Override
    public String execute() throws IOException {
        Item item = this.player.getCurrentRoom().getRoomItem(this.player.getCurrentDirection());
        String s;
        Chest chest = (Chest) item;
        if (this.player.getInventory().hasItem(chest.getKey().toString())) {
            if (chest.isLocked()) chest.setLocked(false);
            else chest.setLocked(true);
            return "You used the key";
        }
       s = "You dont have a key to use it for this item";
        return s;
    }


}
