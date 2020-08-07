package com.World_Navigator.demo.MazeFile;

import static com.World_Navigator.demo.MazeFile.Directions.EAST;
import static com.World_Navigator.demo.MazeFile.Directions.NORTH;
import static com.World_Navigator.demo.MazeFile.Directions.SOUTH;
import static com.World_Navigator.demo.MazeFile.Directions.WEST;

import com.World_Navigator.demo.ItemsFile.Chest;
import com.World_Navigator.demo.ItemsFile.Color;
import com.World_Navigator.demo.ItemsFile.Door;
import com.World_Navigator.demo.ItemsFile.Mirror;
import com.World_Navigator.demo.ItemsFile.Painting;
import com.World_Navigator.demo.ItemsFile.Usable.Flashlight;
import com.World_Navigator.demo.ItemsFile.Usable.Gold;
import com.World_Navigator.demo.ItemsFile.Usable.Key;
import com.World_Navigator.demo.ItemsFile.Usable.usableItem;
import com.World_Navigator.demo.ItemsFile.Wall;
import com.World_Navigator.demo.PersonFile.Inventory;
import com.World_Navigator.demo.PersonFile.Person;
import com.World_Navigator.demo.PersonFile.Player;
import com.World_Navigator.demo.PersonFile.Seller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MazeCreator {

  public static Maze createMaze(MazeFactory factory) {
    Maze firstMaze = factory.makeMaze();
    Room room1 = factory.makeRoom("Kitchen");
    Room room2 = factory.makeRoom("SittingRoom");
    Room room3 = factory.makeRoom("Bedroom");

    Wall wall = new Wall();
    Chest chest1 = new Chest(Color.GREEN, 5);
    chest1.setLocked(true);
    ArrayList<usableItem> chestItems = new ArrayList<usableItem>();
    Flashlight chestFlashlight = new Flashlight(5);
    chestItems.add(chestFlashlight);
    chest1.setChestItemList(chestItems);
    Painting painting = new Painting(Color.GREEN, 5);

    room1.setRoomItems(Directions.NORTH, chest1);
    room1.setRoomItems(Directions.SOUTH, painting);
    room1.setRoomItems(WEST, wall);

    Door door1 = factory.makeDoor(room2, Color.RED, 5, false); // save the opposite door of it and change it when its opened
    Door door2 = factory.makeDoor(room1, Color.RED, 5, false);
    room1.setRoomItems(EAST, door1);
    room1.setExit(EAST, door1);
    room2.setRoomItems(Directions.WEST, door2);
    room2.setExit(WEST, door2);

//    room2.setDark(true);

    Inventory sellerInventory = new Inventory();
    Flashlight sellerFlashlight = new Flashlight(14);
    Key sellerKey = new Key(Color.GREEN, 10);
    sellerInventory.setToInv(sellerFlashlight);
    sellerInventory.setToInv(sellerKey);
    Gold sellerGold = new Gold(50);
    Seller seller = new Seller(sellerInventory, room1, sellerGold);
    room2.setRoomItems(Directions.NORTH, seller);

    Door door3 = factory.makeDoor(room3, Color.BLUE, 5, true);
    Door door4 = factory.makeDoor(room2, Color.BLUE, 5, true);

    room2.setRoomItems(Directions.SOUTH, door3);
    room2.setExit(SOUTH, door3);
    room2.setRoomItems(EAST, wall);

    room3.setRoomItems(NORTH, door4);
    room3.setExit(NORTH, door4);

    Mirror mirror = new Mirror();

    room3.setRoomItems(EAST, mirror);
    room3.setRoomItems(WEST, wall);

    Door exit = new Door(room3, Color.BLUE, 5, true);
    exit.setIsExit();
    room3.setRoomItems(SOUTH, exit);
    room3.setExit(SOUTH, exit);

    firstMaze.addRoom(room1);
    firstMaze.addRoom(room2);
    firstMaze.addRoom(room3);

    ExpireTask mazeTime = new ExpireTask(firstMaze);
    firstMaze.checkTimer();

    return firstMaze;
  }

  //  public Person CreatePlayer(PersonFactory factory) {
  //    //    //    //    player1.putItems(new Flashlight());
  //    //    //    //    player1.listItems();
  //    //    //    player1.addGold(100);
  //    //    //    System.out.println(player1.getGold());
  //    //    //    player1.listItems();
  ////    return player1;
  //  }

  public void Play(Person player) throws IOException {
    BufferedReader in;
    String input;
    String output;

    in = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Welcome to World Navigator game");
    System.out.println("You are starting this game facing the north");
    System.out.println("Please enter a command to start playing");
    System.out.println("You can use the \"help me\" command to check the commands you can use");

    do {
      input = in.readLine();
      player.runCommand(input);
      //      System.out.println(output);

    } while (!"quit".equals(input));
  }
}
