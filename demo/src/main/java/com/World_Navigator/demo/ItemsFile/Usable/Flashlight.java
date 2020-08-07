package com.World_Navigator.demo.ItemsFile.Usable;

public class Flashlight implements usableItem {

  private int numberOfFlashlights = 0;
  private boolean isOn;
  private Gold price;
  private String description;

  public Flashlight(int FlashlightPrice) {
    price = new Gold();
    price.setGold(FlashlightPrice);
    numberOfFlashlights++;
  }

  public boolean isOn() {
    System.out.println("The flashlight is on");
    return isOn;
  }

  public void setOn(boolean on) {
    isOn = on;
  }

  public boolean isOff() {
    System.out.println("The flashlight is off");
    return isOn;
  }

  public void setOff(boolean off) {
    isOn = off;
  }

  public void toggleLight() {
    if (this.isOn) {
      this.setOff(false);
    }
    this.setOn(true);
  }

  public int getNumberOfFlashlights() {
    return numberOfFlashlights;
  }

  public void setNumberOfFlashlights(int numberOfFlashlights) {
    this.numberOfFlashlights = numberOfFlashlights;
  }

  @Override
  public int getPrice() {
    return this.price.getGold();
  }

  @Override
  public void setPrice(int price) {
    this.price.setGold(price);
  }

  @Override
  public Object setDescriptionNoColor() {
    return "Flashlight";
  }

  @Override
  public String setDescription() {
    return description = "Flashlight";
  }

  @Override
  public String toString() {
    return this.setDescription();
  }
}
