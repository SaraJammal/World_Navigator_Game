package com.World_Navigator.demo.PersonFile.Commands;

import com.World_Navigator.demo.ItemsFile.Usable.usableItem;
import com.World_Navigator.demo.MazeFile.Directions;
import com.World_Navigator.demo.PersonFile.Player;
import com.World_Navigator.demo.PersonFile.Seller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.World_Navigator.demo.PersonFile.Commands.Commands.commandWordList;

public class Trade implements Command{

    private final Player player;
    private boolean tradeOn;
    private List<String> input;
    private String action;
    private int item;

    public Trade(Player player){
        this.player = player;
        this.tradeOn = true;
//        this.player.setTrading(true);
    }

    @Override
    public String execute() throws IOException {

        String s;
            Seller seller = (Seller) this.player.getCurrentRoom().getRoomItem(this.player.getCurrentDirection());
            s = "Welcome player. \n You have entered the trade mode \n";
            s = s + "The seller have these items\n\n";

            s = s + seller.getInventory().listItems(seller.getInventory());

            s = s + "\n What action would you like to take\n" +
                "Buy (the item index): to buy from the seller\n "
                + "Sell (the item index): to sell an Item to the seller\n "
                + "List items: to list the items obtained by the seller\n"
                + "Finish trade: exit trade mode";
            return s;
            //TODO: FIX TRADING

            /*// User input trade choice.
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            this.action = in.readLine();
            String lowerCase = action.trim().toLowerCase();
            input = commandWordList(lowerCase);
            if(this.input.get(0).equals("buy")){
                this.item = Integer.parseInt(input.get(1));
                buy(item);
            }else{
                if(this.input.get(0).equals("sell")){
                    this.item = Integer.parseInt(input.get(1));
                    sell(item);
                }else{
                    if(this.input.get(0).equals("list")){
                        list();
                    }else{
                        if(this.input.get(0).equals("finish")){
                           s = "Nice dealing with you";
                            this.tradeOn = false;
                           return s;
                        }else{
                            s = "Invalid command in trade mode";
                            return s;
                        }
                    }

                }
            }*/

    }



    // TODO: replace result with system.out
    public String sell(int item){
        Seller seller = (Seller) player.getCurrentRoom().getRoomItem(player.getCurrentDirection());
        if (item >= player.getInventory().size() || item <0) {
            return "No item available";
        } else {
            usableItem playerItem = player.getInventory().getTheItem(item);
            if (playerItem.getPrice() > seller.getGold()) {
                return "The seller doesnt have enough gold";
            }
            player.sellItem(playerItem, seller);
        }
        return "Item has been sold\n Thank you. ";
    }
    public String list() {
        Seller seller = (Seller) this.player.getCurrentRoom().getRoomItem(Directions.currentDirection);
       return seller.getInventory().listItems(seller.getInventory()) + this.player.getInventory().listItems(this.player.getInventory());
    }

    public String buy(int item) {
        Seller seller = (Seller) player.getCurrentRoom().getRoomItem(player.getCurrentDirection());
        if (item >= seller.getInventory().size()) {
            return "No item available";
        } else {
            usableItem sellerItem = seller.getInventory().getTheItem(item);
            if (sellerItem.getPrice() > player.getGold()) {
                return "You dont have enough gold";
            } else {
                player.buyItem(sellerItem, seller);
                return "You have " + player.getGold() + " " + "The item worth  " + sellerItem.getPrice()+
                "Now you have " + player.getGold();
            }
        }
    }
}
