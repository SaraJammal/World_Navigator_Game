package com.World_Navigator.demo.ItemsFile;

import com.World_Navigator.demo.ItemsFile.Usable.Key;

public class Painting implements Item {

  private Key paintingKey;
  private String description;

  public Painting(Color keyColor, int keyPrice) {
    paintingKey = new Key(keyColor, keyPrice);
  }

  @Override
  public Key getKey() {
    return paintingKey;
  }

  @Override
  public void removeKey() {
    paintingKey = null;
  }

  @Override
  public String setDescription() {
    return this.description = "Painting";
  }

  @Override
  public String toString() {
    return this.setDescription();
  }
}
