package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertTrue;

import com.ltd14.cellgate.model.MapData;
import com.ltd14.cellgate.model.Wall;
import org.junit.Test;

public class MapGeometryTest {

  @Test
  public void testWallsAreWithinBounds() {
    MapGenerator generator = new MapGenerator();
    int width = 1080;
    int height = 1920;
    MapData mapData = generator.generate(width, height, 0);

    float mapHeight = mapData.getMapHeight();

    for (Wall wall : mapData.getWalls()) {
      assertTrue("Wall left should be >= 0", wall.getRect().left >= 0);
      assertTrue("Wall right should be <= width", wall.getRect().right <= width);

      assertTrue("Wall top should be >= 0", wall.getRect().top >= 0);
      assertTrue("Wall bottom should be <= mapHeight", wall.getRect().bottom <= mapHeight);
    }
  }
}
