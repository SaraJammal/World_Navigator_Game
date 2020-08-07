package com.World_Navigator.demo.MazeFile;

import static java.lang.System.exit;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Maze {
  public Timer mazeTimer;
  public int delay;
  public int period;
  public TimerTask mazeTimerTask;
  protected ArrayList<Room> rooms;

  public Maze(boolean timer) {
    rooms = new ArrayList<Room>();
  }

  public Maze() {
    this.rooms = new ArrayList<>();
  }

  public void addRoom(Room room) {
    if (room == null) throw new IllegalArgumentException();
    else {
      rooms.add(room);
    }
  }

  public int getNumOfRooms() {
    return rooms.size();
  }

  public ArrayList getRooms() {
    return rooms;
  }

  public void checkTimer() {
    Timer timer = new Timer();
    timer.schedule(new ExpireTask(this), 600000 /* 60 secs */);
  }

  public void timeExpired() {
    System.out.println("Time has expired, you lost");
    exit(0);
  }
}
