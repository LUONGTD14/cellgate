package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertTrue;

import com.ltd14.cellgate.model.MapData;
import org.junit.Test;

public class MapGeneratorGateTest {

  @Test
  public void testAtLeastOneColumnIsCompletelyOpen() {
    MapGenerator generator = new MapGenerator();
    for (int i = 0; i < 10; i++) {
      MapData mapData = generator.generate(1080, 1920, 0);

      boolean hasPotentialPath = mapData.getWalls().size() < (11 * 21);
      assertTrue("Map must have empty spaces for gates", hasPotentialPath);
    }
  }
}
