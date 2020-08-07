package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.PersonFile.Player;

import java.io.IOException;

public class SwitchLights implements Command {

    private Player player;

    public SwitchLights(Player player){
        this.player = player;
    }

    @Override
    public String execute() throws IOException {
        return "";
    }
}
