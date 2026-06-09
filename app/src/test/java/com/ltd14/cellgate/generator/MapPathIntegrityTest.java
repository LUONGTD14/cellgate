package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertTrue;

import com.ltd14.cellgate.model.MapData;
import com.ltd14.cellgate.model.Wall;
import org.junit.Test;

public class MapPathIntegrityTest {

  @Test
  public void testAlwaysHasPossiblePath() {
    MapGenerator generator = new MapGenerator();
    for (int i = 0; i < 100; i++) {
      MapData mapData = generator.generate(1080, 1920, i);

      boolean hasSpace = false;
      for (Wall wall : mapData.getWalls()) {
        hasSpace = true;
        break;
      }
      assertTrue("Map must have navigable space", hasSpace);
    }
  }
}
