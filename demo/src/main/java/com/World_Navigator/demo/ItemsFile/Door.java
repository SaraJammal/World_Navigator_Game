package com.World_Navigator.demo.ItemsFile;

import com.World_Navigator.demo.ItemsFile.Usable.Key;
import com.World_Navigator.demo.MazeFile.Directions;
import com.World_Navigator.demo.MazeFile.Room;

// locked door subclass
public class Door implements Item {

  private final Room roomTo;
  private final Color doorColor;
  private String description;
  private Room roomFrom;
  private boolean isExit;
  private boolean isOpen;
  private Key doorKey;

  public Door(Room exitRoom, Color doorColor, int keyPrice, boolean isOpen) {
    this.roomTo = exitRoom;
    this.doorColor = doorColor;
    this.isOpen = isOpen;
    this.isExit = false;
    this.doorKey = new Key(doorColor, keyPrice);
  }

  public void createDoorBetweenRooms(
      Room from, Room to, Directions directions, Color color, int keyPrice, boolean isOpen) {
    from.setExit(directions, new Door(to, color, keyPrice, isOpen));
    to.setExit(directions.opposite(directions), new Door(from, color, keyPrice, isOpen));
  }

  public Key getDoorKey() {
    return doorKey;
  }

  public void setDoorKey(Key doorKey) {
    this.doorKey = doorKey;
  }

  public Room getRoomFrom() {
    return roomFrom;
  }

  public Room getRoomTo() {
    return this.roomTo;
  }

  public Color getDoorColor() {
    return this.doorColor;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public void setDoorToOpen(Door door) {
    door.isOpen = true;
  }

  public void setDoorToLocked() {
    this.isOpen = false;
  }

  public boolean isExit() {
    return isExit;
  }

  public void setIsExit() {
    this.isExit = true;
  }

  @Override
  public String toString() {
    return this.setDescription();
  }

  public String setDescription() {
    return this.description = "Door";
  }

  @Override
  public Key getKey() {
    return doorKey;
  }

  @Override
  public void removeKey() {
    doorKey = null;
  }
}
