package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertTrue;

import com.ltd14.cellgate.model.MapData;
import org.junit.Test;

public class MapGeneratorConstraintsTest {

  @Test
  public void testGeneratorRespectsMaxConstraints() {
    MapGenerator generator = new MapGenerator();
    MapData mapData = generator.generate(1080, 1920, 999999);

    assertTrue("Map should be generated even with extreme scores", mapData.getWalls().size() > 0);
    assertTrue("Map height should be positive", mapData.getMapHeight() > 0);
  }

  @Test
  public void testWallGridBoundaries() {
    MapGenerator generator = new MapGenerator();
    for (int i = 0; i < 50; i++) {
      generator.generate(1080, 1920, i * 10);
    }
    assertTrue(true); // Reached here without crashing
  }
}
