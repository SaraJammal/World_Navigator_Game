package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.PersonFile.Player;

import java.io.IOException;

import static java.lang.System.exit;

public class Quit implements Command{

    private Player player;

    public Quit (Player player){
        this.player = player;
    }

    @Override
    public String execute() throws IOException {
       String s = "Game Over";
       return s;
    }


}
