package com.World_Navigator.demo.MazeFile;

public enum Directions {
  NORTH,
  SOUTH,
  EAST,
  WEST,
  currentDirection,
  nextDirection,
  forward,
  backward,
  left,
  right;

  public static final int NOEXIT = -1;

  Directions() {}

  public Directions nextLeft(Directions direction) {
    if (direction == null) {
      System.out.println("NoSuchElementException");
    } else if (this == NORTH && direction == left) {
      return WEST;
    } else if (this == EAST && direction == left) {
      return NORTH;
    } else if (this == SOUTH && direction == left) {
      return EAST;
    } else if (this == WEST && direction == left) {
      return SOUTH;
    }
    return null;
  }

  public Directions nextRight(Directions direction) {
    if (direction == null) {
      System.out.println("NoSuchElementException");
    } else if (this == NORTH && direction == right) {
      return EAST;
    } else if (this == EAST && direction == right) {
      return SOUTH;
    } else if (this == SOUTH && direction == right) {
      return WEST;
    } else if (this == WEST && direction == right) {
      return NORTH;
    }
    return null;
  }

  public Directions opposite(Directions directions) {
    switch (directions) {
      case NORTH:
        return SOUTH;
      case SOUTH:
        return NORTH;
      case WEST:
        return EAST;
      case EAST:
        return WEST;
    }
    return null;
  }
}
