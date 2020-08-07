package com.World_Navigator.demo.ItemsFile;

import com.World_Navigator.demo.ItemsFile.Usable.Key;

public class Mirror implements Item {
  private Key mirrorKey;
  private String description;

  public Mirror() {}

  public Mirror(Color keyColor, int keyPrice) {
    mirrorKey = new Key(keyColor, keyPrice);
  }

  @Override
  public String setDescription() {
    return description = "Mirror";
  }

  @Override
  public Key getKey() {
    return mirrorKey;
  }

  @Override
  public void removeKey() {
    mirrorKey = null;
  }

  @Override
  public String toString() {
    return this.setDescription();
  }
}
