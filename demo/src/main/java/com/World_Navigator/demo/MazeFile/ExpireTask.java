package com.World_Navigator.demo.MazeFile;

import java.util.TimerTask;

class ExpireTask extends TimerTask {
  Maze callbackClass;

  ExpireTask(Maze callbackClass) {
    this.callbackClass = callbackClass;
  }

  public void run() {
    callbackClass.timeExpired();
  }
}
