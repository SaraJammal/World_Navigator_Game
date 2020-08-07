package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.MazeFile.Directions;
import com.World_Navigator.demo.PersonFile.Commands.Navigation.Backward;
import com.World_Navigator.demo.PersonFile.Commands.Navigation.Forward;
import com.World_Navigator.demo.PersonFile.Commands.Navigation.Left;
import com.World_Navigator.demo.PersonFile.Commands.Navigation.Right;
import com.World_Navigator.demo.PersonFile.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Commands {
  List<String> commands =
      new ArrayList<>(
          Arrays.asList(
              "quit",
              "restart",
              "loot",
              "drop",
              "look",
              "trade",
              "buy",
              "sell",
              "go",
              "list",
              "n",
              "s",
              "e",
              "w",
              "forward",
              "backward"));
  List<String> items = new ArrayList<>(Arrays.asList("flashlight", "key", "gold"));

  public static List<String> commandWordList(String input) {
    String trash = " \t,.:;?!\"' ";
    List<String> list = new ArrayList<>();
    StringTokenizer token = new StringTokenizer(input, trash);
    String s;
    while (token.hasMoreTokens()) {
      s = token.nextToken();
      list.add(s);
    }
    return list;
  }

  private static String changeRoomForward(List<String> command, Player player) {
    Forward forward = new Forward(player);
    return forward.execute();
  }

  private static String directNorth(Directions North, Player player) {
   return player.moveInRoom(North);
  }

  private static String directSouth(Directions South, Player player) {
    return  player.moveInRoom(South);
  }

  private static String directEast(Directions East, Player player) {
    return player.moveInRoom(East);
  }

  private static String directWest(Directions West, Player player) {
    return player.moveInRoom(West);
  }

  public String playerCommand(List<String> command) throws IOException {
    String action = command.toString();
    String item;
        switch (command.get(0)) {
          case "n":
              return directNorth(Directions.NORTH, (Player) this);
          case "s":
              return directSouth(Directions.SOUTH, (Player) this);
          case "w":
              return directWest(Directions.WEST, (Player) this);
           case "e":
              return directEast(Directions.EAST, (Player) this);
          case "left":
              return goLeft(Directions.NORTH, (Player) this);
          case "right":
              return goRight(Directions.NORTH, (Player) this);
          case "forward":
              return changeRoomForward(command, (Player) this);
          case "backward":
              return changeRoomBackward(command, (Player) this);
//          case "help":
//            help(command, (Player) this);
//            break;
          case "status":
            return status(command, (Player) this);
//          case "inventory":
//            listInventory(command, (Player) this);
//            break;
          case "quit":
            quit(command, (Player) this);
            break;
          case "restart":
          restart(command, (Player) this);
          break;
          case "trade":
              return trade(command, (Player) this);
          case "sell":
            return sell(command, (Player) this);
          case "buy":
            return buy(command, (Player) this);
          case "look":
              return look(command, (Player) this);
          case "open":
              if (command.size()>1 && command.get(1).equals("door")) return openDoor(command, (Player) this);
              else return openChest(command, (Player) this);
          case "check":
              return check(command, (Player) this);

          case "use": {
              if (command.get(1).equals("flashlight")) {
                 return useFlashlight(command, (Player) this);

              } else if (command.get(1).equals("key")) {
                Player player = (Player) this;
                if (player
                    .getCurrentRoom()
                    .getRoomItem(player.getCurrentDirection())
                    .toString()
                    .equals("Door")) {
                  return useKeyDoor(command, (Player) this);
                } else if (player
                    .getCurrentRoom()
                    .getRoomItem(player.getCurrentDirection())
                    .toString()
                    .equals("Chest")) {
                   return useKeyChest(command, (Player) this);
                }
              }
            }
          case "searchlight":
              return switchLights(command, (Player) this);

          default:
            System.out.println("No valid move");
        }
        return "!!!";
    }

  private String buy(List<String> command, Player player) {
    Command tradeBuy = new Trade(player);
    return ((Trade) tradeBuy).buy(Integer.parseInt(command.get(1)));
  }

  private String sell(List<String> command, Player player) {
    Command tradeSell = new Trade(player);
    return  ((Trade) tradeSell).sell(Integer.parseInt(command.get(1)));
  }

  private String goRight(Directions north, Player player) throws IOException {
    Right right = new Right(player);
    return right.execute();
  }

  private String goLeft(Directions north, Player player) throws IOException {
    Left left  = new Left(player);
    return left.execute();
  }


  private void help(List<String> command, Player player) {
    System.out.println("You can move around the room using the following commands");
    System.out.println("\"n\" to face north side");
    System.out.println("\"s\" to face south side");
    System.out.println("\"e\" to face east side");
    System.out.println("\"w\" to face west side");
    System.out.println("\"forward\" to go through an open door");
    System.out.println("\"backward\" to go back to the previous room through the door");
    System.out.println("\"open door\" to go through the door");
    System.out.println("\"open chest\" to open the chest");
    System.out.println("\" my status\" to check your status");
    System.out.println("\" inventory open\" to check your inventory");
    System.out.println("\" check \" Item name \" \" to check the item in front of you");
    System.out.println("\" check \" Item name \" \" to check the item in front of you");
    System.out.println("\" use \" Item name \" \" to use the item with you");
    System.out.println("\" trade seller \" to trade the seller in front of you");
    System.out.println("\" sell seller \" to trade the seller in front of you");
    System.out.println("\" buy seller \" to trade the seller in front of you");
  }

  private String status(List<String> command, Player player) {
   return "You are in the " + player.getCurrentRoom() + "\n You are facing the "
       + player.getCurrentDirection()
       + "You have " + player.getGold() + " gold" +
    player.getInventory().listItems(player.getInventory());
  }

  private void listInventory(List<String> command, Player player) {
    player.listItems();
  }

  private void restart(List<String> command, Player player) {
    System.out.print("Restarting the game ");
    player.setInventory(player.getInvCopy());
    player.setGold(player.getGoldCopy().getGold());
    player.setCurrentRoom(player.getStartRoom());
  }

  private void quit(List<String> command, Player player) throws IOException {
    Command Quit = new Quit(player);
    Quit.execute();
  }

  private String check(List<String> command, Player player) {
    Check check = new Check(player);
    return check.execute();
  }

  private String changeRoomBackward(List<String> command, Player player) {
    Backward backward = new Backward(player);
    return backward.execute();
  }

  private String switchLights(List<String> command, Player player) {
     return player.pSwitchLight();
  }

  private String useKeyChest(List<String> command, Player player) throws IOException {
    Command UseChestKey = new UseChestKey(player);
    return UseChestKey.execute();
  }

  private String useKeyDoor(List<String> command, Player player) throws IOException {
   Command UserDoorKey = new UseDoorKey(player);
   return UserDoorKey.execute();
  }

  private String useFlashlight(List<String> command, Player player) {
    Command UserFlashlight = new UseFlashlight(player);
    try {
      return UserFlashlight.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "No flash light to be used";
  }

//  private void finishTrade(List<String> command, Player player) {
//    System.out.println("Nice dealing with you");
//  }

  private void checkChestItems(List<String> command, Player player) {
    System.out.println("Chest is opened and you started to look inside");
    Command check = new Check(player);
    try {
      check.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String openChest(List<String> command, Player player) throws IOException {
    if (command.get(1).equals("chest")) {
      Command openChest = new OpenChest(player);
      return  "You try to open the chest" + openChest.execute();
    }
    return " No chest to open";
  }

  private String openDoor(List<String> command, Player player) {
    OpenDoor openDoor = new OpenDoor(player);
    return "You held the door hand and tried to open the door" +  openDoor.execute();
  }

  private String look(List<String> commands, Player player) {
    System.out.println("inside look");
    Look look = new Look(player);
    System.out.println("inside look after creatiion");

    return "On the " + player.getCurrentDirection() + " side of the room you found a " + look.execute();
  }

  private String trade(List<String> command, Player player) throws IOException {
    Command trade = new Trade(player);
   return trade.execute();
}

  public String runCommand(String input) throws IOException {
    List<String> list;
    String lowerCase = input.trim().toLowerCase();
    if (lowerCase.equals(" ")) {
      return "Please enter a valid command";
    }
    list = commandWordList(lowerCase);
    //      System.out.println(list.size());
    if (list.size() >= 1) {
      return playerCommand(list);
    }
    return "Please enter a valid command";
  }
}
