package com.World_Navigator.demo.ItemsFile;

import com.World_Navigator.demo.ItemsFile.Usable.Key;

public class Wall implements Item {

  private String description;

  public Wall() {}

  @Override
  public String setDescription() {
    return this.description = "Wall";
  }

  @Override
  public Key getKey() {
    return null;
  }

  @Override
  public void removeKey() {}

  @Override
  public String toString() {
    return this.setDescription();
  }
}
