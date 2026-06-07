package com.ltd14.cellgate.model;

import java.util.ArrayList;
import java.util.List;

public class MapData {

  private final List<Wall> walls = new ArrayList<>();
  private float mapHeight;

  public List<Wall> getWalls() {
    return walls;
  }

  public void addWall(Wall wall) {
    walls.add(wall);
  }

  public float getMapHeight() {
    return mapHeight;
  }

  public void setMapHeight(float mapHeight) {
    this.mapHeight = mapHeight;
  }
}
