package com.World_Navigator.demo.PersonFile;

import com.World_Navigator.demo.ItemsFile.Usable.Gold;
import com.World_Navigator.demo.ItemsFile.Usable.usableItem;
import com.World_Navigator.demo.MazeFile.Directions;
import com.World_Navigator.demo.MazeFile.Room;
import com.World_Navigator.demo.PersonFile.Commands.Commands;

public class Person extends Commands {

  private Commands commands; // will be expand later(now all commands in player class)
  private Inventory inventory;
  private Room currentRoom;
  private Gold gold = new Gold();
  private Directions directions;

  public Person() {}

  public Person(Inventory inventory, Room currentRoom, Gold gold) {
    this.inventory = inventory;
    this.currentRoom = currentRoom;
    this.gold = gold;
  }

  public void putItems(usableItem item) {
    inventory.setToInv(item);
  }

  public void deleteItems(usableItem item) {
    inventory.removeFromInv(item);
  }

  public void listItems() {
    System.out.println("These Items are in the inventory ");
    this.inventory.listItems(inventory);
    System.out.print("And you have " + this.getGold() + " gold \n");
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public Room getCurrentRoom() {
    return this.currentRoom;
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public int setGold(int amount) {
    this.gold.setGold(amount);
    return gold.getGold();
  }

  public int getGold() {
    return gold.getGold();
  }

  public String setDescription() {
    return "Person";
  }
}
