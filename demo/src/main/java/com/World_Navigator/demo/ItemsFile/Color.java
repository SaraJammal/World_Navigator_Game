package com.World_Navigator.demo.ItemsFile;

public enum Color {
  RED("red"),
  BLUE("blue"),
  GREEN("green"),
  YELLOW("yellow"),
  NONE("none");

  private String aName;

  private Color(final String pName) {
    this.aName = pName;
  }

  public static Color fromString(final String pName) {
    for (Color color : Color.values()) {
      if (color.getName().equalsIgnoreCase(pName)) return color;
    }
    return null;
  }

  public String getName() {
    return this.aName;
  }
}
