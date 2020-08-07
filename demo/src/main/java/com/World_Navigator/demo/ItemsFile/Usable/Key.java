package com.World_Navigator.demo.ItemsFile.Usable;

import com.World_Navigator.demo.ItemsFile.Color;

public class Key implements usableItem {

  private String description;
  private Gold price;
  private Color keyColor;

  public Key(Color keyColor, int keyPrice) {
    this.keyColor = keyColor;
    price = new Gold();
    this.price.setGold(keyPrice);
  }

  public Color getKeyColor() {
    return keyColor;
  }

  public void setKeyColor(Color keyColor) {
    this.keyColor = keyColor;
  }

  @Override
  public int getPrice() {
    return this.price.getGold();
  }

  @Override
  public void setPrice(int price) {
    this.price.setGold(price);
  }

  @Override
  public String setDescription() {
    return description = this.keyColor + " Key";
  }

  @Override
  public String setDescriptionNoColor() {
    return description = "Key";
  }

  @Override
  public String toString() {
    return this.setDescription();
  }
}
