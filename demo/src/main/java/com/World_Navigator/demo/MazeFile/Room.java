package com.World_Navigator.demo.MazeFile;

import com.World_Navigator.demo.ItemsFile.Door;
import com.World_Navigator.demo.ItemsFile.Item;
import java.util.HashMap;

public class Room {

  private HashMap<Directions, Item> roomItems;
  private HashMap<Directions, Door> roomExits;
  private int roomIndex = -1;
  private String Description;
  private boolean isStart;
  private boolean isDark;
  private boolean hasLight;
  private int numberOfPlayers;

  public void setRoomItems(
      HashMap<Directions, Item> roomItems) {
    this.roomItems = roomItems;
  }

  public boolean isStart() {
    return isStart;
  }

  public void setStart(boolean start) {
    isStart = start;
  }

  public int getNumberOfPlayers() {
    return numberOfPlayers;
  }

  public void setNumberOfPlayers(int numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
  }

  public Room(String description) {
    roomIndex = roomIndex + 1;
    Description = description;
    if (roomIndex == 0) isStart = true;
    else isStart = false;
    isDark = false;
    roomItems = new HashMap<Directions, Item>();
    roomExits = new HashMap<Directions, Door>();
    hasLight = true;
    numberOfPlayers = 0;
  }

  public int getRoomIndex() {
    return roomIndex;
  }

  public void setRoomIndex(int roomIndex) {
    this.roomIndex = roomIndex;
  }

  public boolean isHasLight() {
    return hasLight;
  }

  public void setHasLight(boolean hasLight) {
    this.hasLight = hasLight;
  }

  public boolean getIsStart() {
    return isStart;
  }

  public boolean isDark() {
    return isDark;
  }

  public void setDark(boolean dark) {
    this.isDark = dark;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public void getRoomNumber() {
    System.out.println("room number is " + roomIndex);
  }

  public void setRoomItems(Directions direction, Item item) {
    roomItems.put(direction, item);
  }

  public Item getRoomItem(Directions direction) {
    return roomItems.get(direction);
  }

  public HashMap<Directions, Item> getRoomItems() {
    return roomItems;
  }

  public void replaceItem(Directions direction, Item theItem) {
    this.roomItems.put(direction, theItem);
  }

  public void removeItem(Item itemName) {
    roomItems.remove(itemName);
  }

  public HashMap<Directions, Item> getRoomDoors() {
    return roomItems;
  }

  public Door[] getRoomExits() {
    return roomExits.values().toArray(new Door[0]);
  }

  public void setRoomExits(HashMap<Directions, Door> roomExits) {
    this.roomExits = roomExits;
  }

  public Door getRoomDoor(Directions directions) {
    return roomExits.get(directions);
  }

  public Door getExit(final Directions directions) {
    return roomExits.get(directions);
  }

  public void setExit(final Directions directions, final Door door) {
    roomExits.put(directions, door);
  }

  public Door getDoor(final Directions directions) {
    return roomExits.get(directions);
  }

  public void setDoor(final Directions directions, final Door door) {
    roomExits.put(directions, door);
  }

  @Override
  public String toString() {
    return getDescription();
  }
}
