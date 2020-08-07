package com.World_Navigator.demo.ItemsFile.Usable;

public class Gold {

  private int gold;
  private String description;

  public Gold() {
    gold = 0;
  }

  public Gold(int amount) {
    gold = amount;
  }

  public int getGold() {
    return gold;
  }

  public void setGold(int amount) {
    this.gold = amount;
  }

  public String setDescription() {
    return this.description = "Gold";
  }

  @Override
  public String toString() {
    return this.description;
  }
}
