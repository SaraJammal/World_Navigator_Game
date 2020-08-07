package com.World_Navigator.demo.PersonFile;

import com.World_Navigator.demo.ItemsFile.Usable.usableItem;
import java.util.ArrayList;

public class Inventory {
  private int numberOfItems;
  private ArrayList<usableItem> inv;

  public Inventory() {
    inv = new ArrayList<usableItem>();
    numberOfItems = 0;
  }

  public int size() {
    return inv.size();
  }

  public void setToInv(usableItem item) {
    this.numberOfItems++;
    this.inv.add(item);
  }

  public void removeFromInv(usableItem item) {
    this.numberOfItems--;
    this.inv.remove(item);
  }

  public int getNumberOfItems() {
    return numberOfItems;
  }

  public void setNumberOfItems(int numberOfItems) {
    this.numberOfItems = numberOfItems;
  }

  public String listItems(Inventory inventory) {
    String s = " ";
    for (int i = 0; i < inventory.getNumberOfItems(); i++) {
      s = s +
          inventory.inv.get(i).toString() + " and worth " + inventory.inv.get(i).getPrice() + "\n ";
    }
    return s;
  }

  public boolean hasItem(String item) {
    for (int i = 0; i < numberOfItems; i++) {
      if (inv.get(i).toString().equals(item)) return true;
    }
    return false;
  }

  // Takes the index of the wanted item
  public usableItem getTheItem(int item) {
    return inv.get(item);
  }

  public ArrayList<usableItem> getInv() {
    return inv;
  }

  public void setInv(ArrayList<usableItem> inv) {
    this.inv = inv;
  }

  public boolean isEmpty() {
    return inv.size() == 0;
  }
}
