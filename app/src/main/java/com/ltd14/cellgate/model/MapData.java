package com.ltd14.cellgate.model;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.List;

public class MapData {

  private final List<Wall> walls = new ArrayList<>();
  private float mapHeight;
  private Bitmap mapBitmap;

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

  public Bitmap getMapBitmap() {
    return mapBitmap;
  }

  public void setMapBitmap(Bitmap mapBitmap) {
    this.mapBitmap = mapBitmap;
  }
  
  public void recycle() {
    if (mapBitmap != null && !mapBitmap.isRecycled()) {
      mapBitmap.recycle();
      mapBitmap = null;
    }
  }
}
