package com.World_Navigator.demo.MazeFile;

import com.World_Navigator.demo.ItemsFile.Chest;
import com.World_Navigator.demo.ItemsFile.Color;
import com.World_Navigator.demo.ItemsFile.Door;
import com.World_Navigator.demo.ItemsFile.Item;
import com.World_Navigator.demo.ItemsFile.Mirror;
import com.World_Navigator.demo.ItemsFile.Painting;
import com.World_Navigator.demo.ItemsFile.Usable.Gold;
import com.World_Navigator.demo.ItemsFile.Usable.usableItem;
import com.World_Navigator.demo.ItemsFile.Wall;
import java.util.ArrayList;

public class MazeFactory {
  private static MazeFactory oneFactory = null;

  public MazeFactory() {}

  public static MazeFactory instance() {
    if (oneFactory == null) oneFactory = new MazeFactory();
    return oneFactory;
  }

  public Maze makeMaze() {
    return new Maze();
  }

  public Room makeRoom(String description) {
    return new Room(description);
  }

  public Item makeWall() {
    return new Wall();
  }

  public Chest makeChest(Gold gold, ArrayList<usableItem> items) {
    Chest chest = new Chest();
    chest.setChestItemList(items);
    chest.setChestGold(gold);
    return chest;
  }

  public Chest makeChest(Color color, int keyPrice) {
    return new Chest(color, keyPrice);
  }

  public Door makeDoor(Room to, Color color, int keyPrice, boolean open) {
    return new Door(to, color, keyPrice, open);
  }

  public Mirror makeMirror(Color key, int keyPrice) {
    return new Mirror(key, keyPrice);
  }

  public Painting makePainting(Color backOfPainting, int pirce) {
    return new Painting(backOfPainting, pirce);
  }
}
