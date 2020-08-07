package com.World_Navigator.demo.PersonFile;

import com.World_Navigator.demo.ItemsFile.Usable.Gold;
import com.World_Navigator.demo.ItemsFile.Usable.usableItem;
import com.World_Navigator.demo.MazeFile.Directions;
import com.World_Navigator.demo.MazeFile.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import lombok.Data;
import org.springframework.lang.NonNull;

public class Player extends Person {

  private final String name;
  private Directions currentDirection;
  private Stack<Directions> pMovesStack; // for further use
  private boolean won;
  private Inventory invCopy;
  private Gold goldCopy;
  private Room startRoom;

  public boolean isTrading() {
    return isTrading;
  }

  public void setTrading(boolean trading) {
    isTrading = trading;
  }

  private String ip;
  private boolean isTrading;

  public Player(String pName, String pIp) {
    super();
    name = pName;
    ip = pIp;
    currentDirection = Directions.NORTH;
    won = false;
    pMovesStack = new Stack<Directions>();
  }

  public Player(Inventory inventory, Room currentRoom, Gold gold, String pName) {
    super(inventory, currentRoom, gold);
    name = pName;
    currentDirection = Directions.NORTH;
    won = false;
    invCopy = inventory;
    goldCopy = gold;
    startRoom = currentRoom;
  }

  public void setIp(String pIp) {
    ip = pIp;
  }

  public String getIp() {
    return ip;
  }

  // Maybe delete if not used
  public void listInventory() {
    for (int i = 0; i < getInventory().size(); i++) {
      System.out.println(this.getInventory().getInv().get(i).toString());
    }
  }

  public Room getStartRoom() {
    return startRoom;
  }

  public void setStartRoom(Room startRoom) {
    this.startRoom = startRoom;
    this.setCurrentRoom(startRoom);
  }

  public String getNameString() {
    return this.name;
  }

  public void getName() {
    System.out.println(this.name);
  }

  public boolean isWon() {
    return won;
  }

  public void setWon(boolean won) {
    this.won = won;
  }

  public Inventory getInvCopy() {
    return invCopy;
  }

  public void setInvCopy(Inventory invCopy) {
    this.invCopy = invCopy;
  }

  public Gold getGoldCopy() {
    return goldCopy;
  }

  public void setGoldCopy(Gold goldCopy) {
    this.goldCopy = goldCopy;
  }

  public void playerStatus() {
    System.out.println("Hello " + this.getNameString());
  }

  public Directions getCurrentDirection() {
    System.out.println("inside player direction");

    return currentDirection;
  }

  public void resetPlayerMoves() {
    pMovesStack.clear();
  }

  public void changeRoom(final Room roomTo, Directions directions) {
    this.setCurrentRoom(roomTo);
    this.currentDirection = directions;
  }

  public boolean move(Directions directions) {
    if (canMove(directions)) {
      changeRoom(getCurrentRoom().getDoor(directions).getRoomTo(), this.getCurrentDirection());
      return true;
    }
    return false;
  }
  // If room is NOT dark and in front of Door and its open
  // the player moves
  public boolean canMove(Directions directions) {
    if (getCurrentRoom().isDark()) return false;
    else {
      if (!(getCurrentRoom().getRoomItem(directions).toString().equals("Door"))) {
        System.out.println("No Door ahead");
        return false;
      }
      if (!(getCurrentRoom().getExit(directions).isOpen())) {
        System.out.println("Door is locked");
        return false;
      }
    }

    return true;
  }

  public boolean canGoBack() {
    return !pMovesStack.empty();
  }

  public boolean canMoveInRoom(Directions directions) {
    return !this.getCurrentRoom().isDark();
  }

  public String moveInRoom(Directions directions) {
    String s;
    if (canMoveInRoom(directions))
      switch (directions) {
        case NORTH:
          {
            this.currentDirection = Directions.NORTH;
            return "You are facing north";
          }
        case SOUTH:
          {
            this.currentDirection = Directions.SOUTH;
            return "You are facing south";
          }
        case WEST:
          {
            this.currentDirection = Directions.WEST;
            return "You are facing west";
          }
        case EAST:
          {
            this.currentDirection = Directions.EAST;
            return "You are facing east";
          }
        default:
          System.out.println("No Direction to look at");
          break;
      }
    return "No Direction to look at";
  }

  //  public void look() {
  //    if (!this.getCurrentRoom().isDark()) {
  //
  // System.out.println(this.getCurrentRoom().getRoomItem(this.currentDirection).setDescription());
  //    } else {
  //      if (this.getCurrentRoom().isHasLight()) {
  //        this.pSwitchLight();
  //        System.out.println(
  //            this.getCurrentRoom().getRoomItem(this.currentDirection).setDescription());
  //
  //      } else System.out.println("It's Dark!");
  //    }
  //  }

  //  public void Check() {
  ////    if (!this.getCurrentRoom().isDark()) {
  ////      Item item = this.getCurrentRoom().getRoomItem(currentDirection);
  ////      switch (item.toString()) {
  ////        case "Mirror":
  ////          {
  ////            if (item.getKey() == null) {
  ////              System.out.println("A black shadow appeared!! \n Oh, its you!");
  ////              break;
  ////            }
  ////            this.putItems(item.getKey());
  ////            item.removeKey();
  ////            System.out.println("The " + item.getKey().getKeyColor() + "is acquired");
  ////            break;
  ////          }
  ////        case "Painting":
  ////          {
  ////            if (item.getKey() == null) {
  ////              System.out.println("Nothing behind the painting");
  ////              break;
  ////            }
  ////            this.putItems(item.getKey());
  ////            item.removeKey();
  ////            break;
  ////          }
  ////        case "Chest":
  ////          {
  ////            // loot;
  ////            //            check and when checked
  ////            Chest chest = (Chest) item;
  ////            if (chest.isLocked()) {
  ////              System.out.println(
  ////                  "Chest is locked " + chest.getKey().getKeyColor() + " is needed to unlock
  // ");
  ////              break;
  ////            } else {
  ////              System.out.println("Chest is opened and you started to loot what's inside");
  ////              chest.listItemsInChest();
  ////              LootItems(chest.getItemsInChest());
  ////              LootGold(chest.getChestGold());
  ////              if (chest.getChestGold() != null) LootGold(chest.getChestGold());
  ////              chest.getItemsInChest().clear();
  ////              chest.getChestGold().setGold(0);
  ////            }
  ////            break;
  ////          }
  ////        case "Door":
  ////          {
  ////            Door door = (Door) item;
  ////            if (door.isOpen()) {
  ////              System.out.println("Door is open");
  ////              break;
  ////            } else {
  ////              System.out.println("Door is locked and " + door.getKey() + " is required");
  ////            }
  ////            break;
  ////          }
  ////      }
  ////    }
  //  }

  public void LootGold(@NonNull Gold chestGold) {
    int g = this.setGold(this.getGold() + chestGold.getGold());
    System.out.println("You added " + g + " gold to your inventory");
  }

  public void LootItems(@NonNull ArrayList<usableItem> items) {
    for (usableItem item : items) {
      this.putItems(item);
    }
    items.clear();
  }

  public void OpenChest() throws IOException {
    //    Command openChest = new OpenChest(this);
    //    openChest.execute();
    //    if (this.getCurrentRoom().getRoomItem(currentDirection).toString().equals("Chest")) {
    //      Chest chest = (Chest) this.getCurrentRoom().getRoomItem(currentDirection);
    //
    //      Key chestKey = chest.getKey();
    //      if (chest.isLocked()) {
    //        Inventory inventory = this.getInventory();
    //        if (inventory.isEmpty()) {
    //          System.out.println("You dont have any items");
    //          return;
    //        }
    //        ArrayList<usableItem> playerInv = this.getInventory().getInv();
    //        for (int i = 0; i < this.getInventory().size(); i++) {
    //          if (playerInv.get(i).toString().equals("Key")) {
    //            Key playerKey = (Key) playerInv.get(i);
    //            if (chestKey.getKeyColor() == playerKey.getKeyColor()) {
    //              System.out.println("The" + chest.getKey().getKeyColor() + " key will be used");
    //              chest.setLocked(false);
    //              return;
    //            }
    //          }
    //        }
    //        System.out.println(chest.getKey().getKeyColor() + " key is required");
    //        return;
    //      }
    //      Check check = new Check(this);
    //      check.execute();
    //    } else System.out.println("Nothing to open");
  }

  //  public void pUseKeyChest() {
  //    Item item = this.getCurrentRoom().getRoomItem(this.getCurrentDirection());
  //    Chest chest = (Chest) item;
  //    if (this.getInventory().hasItem(chest.getKey().toString())) {
  //      if (chest.isLocked()) chest.setLocked(false);
  //      else chest.setLocked(true);
  //    } else {
  //      System.out.println("You dont have a key to use it for this item");
  //    }
  //  }

  //  public void pUseKeyDoor() {
  //    Item item = this.getCurrentRoom().getRoomItem(this.getCurrentDirection());
  //    Door door = (Door) item;
  //    if (this.getInventory().hasItem(door.getKey().toString())) {
  //      if (door.isOpen()) door.setDoorToOpen(door);
  //      else door.setDoorToLocked();
  //    } else {
  //      System.out.println("You dont have a key to use it for this item");
  //    }
  //  }

  //  public void pUseFlashlight() {
  //    Inventory inv = this.getInventory();
  //    for (int i = 0; i < inv.size(); i++) {
  //      if (inv.getInv().get(i).toString().equals("Flashlight")) {
  //        Flashlight flashlight = (Flashlight) inv.getInv().get(i);
  //        flashlight.toggleLight();
  //        if (flashlight.isOn()) {
  //          this.getCurrentRoom().setDark(false);
  //        }
  //      }
  //    }
  //  }

  //  public void OpenDoor() {
  //    if (this.getCurrentRoom().getRoomItem(this.getCurrentDirection()).toString().equals("Door"))
  // {
  //      Door door = this.getCurrentRoom().getRoomDoor(this.getCurrentDirection());
  //      if (door.isOpen()) {
  //        System.out.println("The door is opened");
  //      }
  //      if (door.getKey() == null) {
  //        this.changeRoom(door.getRoomTo(), this.getCurrentDirection());
  //      } else {
  //        Key doorKey = door.getDoorKey();
  //        if (!door.isOpen()) {
  //          Inventory inventory = this.getInventory();
  //          if (inventory.isEmpty() || inventory == null) {
  //            System.out.println("You don't have any items");
  //            return;
  //          }
  //          ArrayList<usableItem> playerInv = this.getInventory().getInv();
  //          for (int i = 0; i < this.getInventory().size(); i++) {
  //            if (playerInv.get(i).setDescriptionNoColor().equals("Key")) {
  //              Key playerKey = (Key) playerInv.get(i);
  //              if (doorKey.getKeyColor() == playerKey.getKeyColor()) {
  //                System.out.println("The" + door.getKey().getKeyColor() + " key will be used");
  //                door.setDoorToOpen(door);
  //              }
  //            }
  //          }
  //        } else System.out.println(door.getKey().getKeyColor() + " is required");
  //      }
  //    } else System.out.println("Nothing to open");
  //
  //  }

  public void Trade(String command, usableItem item) {
    Seller seller = (Seller) getCurrentRoom().getRoomItem(currentDirection);
    if (seller.toString().equals("Seller")) {
      switch (command) {
        case "List":
          seller.listSellerItems();
        case "buy":
          this.buyItem(item, seller);
        case "sell":
          this.sellItem(item, seller);
      }
    }
  }

  public void sellItem(usableItem item, Seller seller) {
    seller.putItems(item);
    seller.setGold(seller.getGold() + item.getPrice());
    this.setGold(this.getGold() + item.getPrice());
    this.deleteItems(item);
  }

  public void buyItem(usableItem item, Seller seller) {
    if (this.getGold() >= item.getPrice()) {
      this.setGold(this.getGold() - item.getPrice());
      this.putItems(item);
      seller.deleteItems(item);
      seller.setGold(seller.getGold() + item.getPrice());
    }
  }

  public String pSwitchLight() {
    if (this.getCurrentRoom().isHasLight()) {
      if (this.getCurrentRoom().isDark()) {
        this.getCurrentRoom().setDark(false);
        return "Room is lit";
      } else {
        this.getCurrentRoom().setDark(true);
        return "Room is dark";
      }
    }
    return "trying to switch the room's light";
  }
}