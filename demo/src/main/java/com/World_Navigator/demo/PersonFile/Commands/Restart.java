package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.MazeFile.Maze;
import com.World_Navigator.demo.PersonFile.Player;

import java.io.IOException;

public class Restart implements Command{

    private Player player;

    public Restart(Player player, Maze maze){

    }

    @Override
    public String execute() throws IOException {
        return "Restarted";
    }
}
