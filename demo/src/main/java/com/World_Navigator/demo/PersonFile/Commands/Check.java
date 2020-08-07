package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.ItemsFile.Chest;
import com.World_Navigator.demo.ItemsFile.Door;
import com.World_Navigator.demo.ItemsFile.Item;
import com.World_Navigator.demo.PersonFile.Player;

public class Check implements Command{

    private Item item;
    private Player player;
    public Check(Player player){
      this.player = player;
      this.item = player.getCurrentRoom().getRoomItem(player.getCurrentDirection());
    }

  @Override
  public String execute() {
    String s;
    if (!this.player.getCurrentRoom().isDark()) {
      //      Item item = player.getCurrentRoom().getRoomItem(player.getCurrentDirection());
      switch (this.item.toString()) {
        case "Mirror":
          {
            if (this.item.getKey() == null) {
              s = "A black shadow appeared!! \n Oh, its you!";
              return s;
            }
            this.player.putItems(this.item.getKey());
            this.item.removeKey();
            s = "The " + this.item.getKey().getKeyColor() + "is acquired";
            return s;
          }
        case "Painting":
          {
            if (item.getKey() == null) {
              s = "Nothing behind the painting";
              return s;
            }
            s = "Key color: " + item.getKey().getKeyColor();
            player.putItems(item.getKey());
            item.removeKey();
            return s;
          }
        case "Chest":
          {
            // loot;
            //            check and when checked
            Chest chest = (Chest) item;
            if (chest.isLocked()) {
              s = "Chest is locked " + chest.getKey().getKeyColor() + " key is needed to unlock it";
              return s;
            } else {
              s = "Chest is opened and you started to loot what's inside\n" + chest.listItemsInChest();
              player.LootItems(chest.getItemsInChest());
              player.LootGold(chest.getChestGold());
              if (chest.getChestGold() != null) player.LootGold(chest.getChestGold());
              chest.getItemsInChest().clear();
              chest.getChestGold().setGold(0);
              return s;
            }
          }
        case "Door":
          {
            Door door = (Door) item;
            if (door.isOpen()) {
              s = "Door is open";
              return s;
            } else {
              s = "Door is locked and \" + door.getKey() + \" is required";
              return s;
            }
          }
      }
    }
    return "Nothing in front of you";
  }
}
