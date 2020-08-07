package com.World_Navigator.demo.MazeFile;

import com.World_Navigator.demo.PersonFile.PersonFactory;
import java.io.IOException;

public class Play_Main {
  public static void main(String[] args) throws IOException {
    MazeFactory mazeFactory = MazeFactory.instance(); // Single maze in the game
    MazeCreator mazeCreator = new MazeCreator();
//        game.CreateTheMaze(mazeFactory);
  }
}
