package com.World_Navigator.demo.PersonFile;

import com.World_Navigator.demo.ItemsFile.Item;
import com.World_Navigator.demo.ItemsFile.Usable.Gold;
import com.World_Navigator.demo.ItemsFile.Usable.Key;
import com.World_Navigator.demo.MazeFile.Room;

public class Seller extends Person implements Item {
  private String description;

  public Seller(Inventory inventory, Room currentRoom, Gold gold) {
    super(inventory, currentRoom, gold);
  }

  @Override
  public String setDescription() {
    return description = "Seller";
  }

  @Override
  public Key getKey() {
    return null;
  }

  @Override
  public void removeKey() {}

  @Override
  public String toString() {
    return this.setDescription();
  }

  public void listSellerItems() {
    System.out.println("The seller has these Items:");
    for (int i = 0; i < this.getInventory().size(); i++) {}
  }
}
