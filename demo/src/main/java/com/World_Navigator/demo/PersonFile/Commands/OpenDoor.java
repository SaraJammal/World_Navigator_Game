package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.ItemsFile.Door;
import com.World_Navigator.demo.ItemsFile.Usable.Key;
import com.World_Navigator.demo.ItemsFile.Usable.usableItem;
import com.World_Navigator.demo.PersonFile.Inventory;
import com.World_Navigator.demo.PersonFile.Player;

import java.util.ArrayList;

public class OpenDoor implements Command{

    private Player player;

    public OpenDoor(Player player){
        this.player = player;
    }

    @Override
    public String execute() {
        String s;
        if (this.player.getCurrentRoom().getRoomItem(this.player.getCurrentDirection()).toString().equals("Door")) {
            Door door = this.player.getCurrentRoom().getRoomDoor(this.player.getCurrentDirection());
            Door backDoor = this.player.getCurrentRoom().getRoomDoor(this.player.getCurrentDirection().opposite(this.player.getCurrentDirection()));
            if (door.isOpen()) {
                s = "The door is opened";
                //backDoor.setDoorToOpen(backDoor);
                return s;
            }
            if (door.getKey() == null) {
                this.player.changeRoom(door.getRoomTo(), this.player.getCurrentDirection());
            } else {
                Key doorKey = door.getDoorKey();
                if (!door.isOpen()) {
                    Inventory inventory = this.player.getInventory();
                    if (inventory == null || inventory.isEmpty()) {
                        s = "You don't have any items";
                        return s;
                    }
                    ArrayList<usableItem> playerInv = this.player.getInventory().getInv();
                    for (int i = 0; i < this.player.getInventory().size(); i++) {
                        if (playerInv.get(i).setDescriptionNoColor().equals("Key")) {
                            Key playerKey = (Key) playerInv.get(i);
                            if (doorKey.getKeyColor() == playerKey.getKeyColor()) {
                                s = "The" + door.getKey().getKeyColor() + " key will be used";
                                door.setDoorToOpen(door);
                                return s;
//                                backDoor.setDoorToOpen(backDoor);
                                //problem accessing the opposite room
                            }
                        }
                    }
                } else{
                    s = door.getKey().getKeyColor() + " is required";
                    return s;
                }
            }
        }
        s = "Nothing to open";
        return s;
    }
}
