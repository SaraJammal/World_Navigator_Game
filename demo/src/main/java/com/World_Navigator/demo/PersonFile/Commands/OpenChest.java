package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.ItemsFile.Chest;
import com.World_Navigator.demo.ItemsFile.Usable.Key;
import com.World_Navigator.demo.ItemsFile.Usable.usableItem;
import com.World_Navigator.demo.PersonFile.Inventory;
import com.World_Navigator.demo.PersonFile.Player;

import java.io.IOException;
import java.util.ArrayList;

public class OpenChest implements Command{

    private Player player;

    public OpenChest(Player player){
        this.player = player;
    }
    @Override
    public String execute() throws IOException {
        String s;
        if (this.player.getCurrentRoom().getRoomItem(player.getCurrentDirection()).toString().equals("Chest")) {
            Chest chest = (Chest) this.player.getCurrentRoom().getRoomItem(player.getCurrentDirection());

            Key chestKey = chest.getKey();
            if (chest.isLocked()) {
                Inventory inventory = this.player.getInventory();
                if (inventory.isEmpty()) {
                    s = "You dont have any items";
                    return s;
                }
                ArrayList<usableItem> playerInv = this.player.getInventory().getInv();
                for (int i = 0; i < this.player.getInventory().size(); i++) {
                    if (playerInv.get(i).toString().equals("Key")) {
                        Key playerKey = (Key) playerInv.get(i);
                        if (chestKey.getKeyColor() == playerKey.getKeyColor()) {
                            s = "The" + chest.getKey().getKeyColor() + " key will be used";
                            chest.setLocked(false);
                            return s;
                        }
                    }
                }
                s = chest.getKey().getKeyColor() + " key is required";
                return s;
            }
            Check check = new Check(this.player);
            check.execute();
        }
        s = "Nothing to open";
        return s;
    }


}
