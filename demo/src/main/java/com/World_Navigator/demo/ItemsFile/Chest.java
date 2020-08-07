package com.World_Navigator.demo.ItemsFile;

import com.World_Navigator.demo.ItemsFile.Usable.Gold;
import com.World_Navigator.demo.ItemsFile.Usable.Key;
import com.World_Navigator.demo.ItemsFile.Usable.usableItem;
import java.util.ArrayList;

public class Chest implements Item {

  private ArrayList<usableItem> items;
  private Gold chestGold;
  private Key chestKey;
  private boolean isLocked;
  private Color chestColor;

  public Chest() {
    chestGold = new Gold();
    items = new ArrayList<usableItem>();
    isLocked = false;
  }

  public Chest(Color keyColor, int price) {
    chestColor = keyColor;
    chestGold = new Gold();
    items = new ArrayList<usableItem>();
    isLocked = true;
    chestKey = new Key(keyColor, price);
  }

  public Color getChestColor() {
    return chestColor;
  }

  public void setChestColor(Color chestColor) {
    this.chestColor = chestColor;
  }

  public Gold getChestGold() {
    return chestGold;
  }

  public void setChestGold(Gold chestGold) {
    this.chestGold = chestGold;
  }

  public ArrayList<usableItem> getItemsInChest() {
    return items;
  }

  public void setItemsInChest(usableItem item) {
    if (item != null) {
      this.items.add(item);
    }
  }

  public void setChestItemList(ArrayList<usableItem> items) {
    this.items = items;
  }

  public boolean isLocked() {
    return this.isLocked;
  }

  public void setLocked(boolean locked) {
    isLocked = locked;
  }

  public void openChest() {
    this.isLocked = true;
  }

  public String listItemsInChest() {
    StringBuilder sb = new StringBuilder();
    sb.append("The Chest contains:\n");
    for (int i = 0; i < items.size(); i++) {
      sb.append(items.get(i));
    }
    return sb.toString();
  }

  @Override
  public String setDescription() {
    String description;
    return description = "Chest";
  }

  @Override
  public Key getKey() {
    return chestKey;
  }

  @Override
  public void removeKey() {
    chestKey = null;
  }

  @Override
  public String toString() {
    return this.setDescription();
  }
}
