package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.ItemsFile.Usable.Flashlight;
import com.World_Navigator.demo.PersonFile.Inventory;
import com.World_Navigator.demo.PersonFile.Player;

import java.io.IOException;

public class UseFlashlight implements Command{

    private Player player;

    public UseFlashlight(Player player){
        this.player = player;
    }

    @Override
    public String execute() throws IOException {
        Inventory inv = this.player.getInventory();
        for (int i = 0; i < inv.size(); i++) {
            if (inv.getInv().get(i).toString().equals("Flashlight")) {
                Flashlight flashlight = (Flashlight) inv.getInv().get(i);
                flashlight.toggleLight();
                if (flashlight.isOn()) {
                    this.player.getCurrentRoom().setDark(false);
                }
            }
        }
        return "You used the flashlight";
    }


}
