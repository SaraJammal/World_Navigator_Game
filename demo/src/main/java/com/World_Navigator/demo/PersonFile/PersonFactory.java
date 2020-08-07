package com.World_Navigator.demo.PersonFile;

import com.World_Navigator.demo.ItemsFile.Usable.Gold;

public class PersonFactory {

  public PersonFactory() {}

  public static Player makePlayer(String name) {
    return new Player(name, "0");
  }

  public Inventory makeInventory() {
    return new Inventory();
  }

  //  public usableItem putFlashLight() {
  //    return new Flashlight();
  //  }
  //
  //  public usableItem putKey(Color color) {
  //    return new Key(color);
  //  }

  public Gold putGold(int amount) {
    return new Gold(amount);
  }
}
