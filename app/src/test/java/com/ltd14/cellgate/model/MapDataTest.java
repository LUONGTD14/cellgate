package com.ltd14.cellgate.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MapDataTest {
  @Test
  public void testMapDataProperties() {
    MapData mapData = new MapData();

    float testHeight = 5000f;
    mapData.setMapHeight(testHeight);
    assertEquals(testHeight, mapData.getMapHeight(), 0.01f);

    Wall wall1 = new Wall(0, 0, 10, 10);
    Wall wall2 = new Wall(20, 20, 30, 30);

    mapData.addWall(wall1);
    mapData.addWall(wall2);

    assertEquals(2, mapData.getWalls().size());
    assertEquals(wall1, mapData.getWalls().get(0));
    assertEquals(wall2, mapData.getWalls().get(1));
  }
}
