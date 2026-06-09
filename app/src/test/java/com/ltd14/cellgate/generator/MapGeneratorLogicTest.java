package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ltd14.cellgate.model.MapData;
import org.junit.Test;

public class MapGeneratorLogicTest {

  @Test
  public void testMapHasAtLeastOnePath() {
    MapGenerator generator = new MapGenerator();
    MapData mapData = generator.generate(1080, 1920, 10);

    assertNotNull(mapData);
    assertTrue(mapData.getMapHeight() > 0);
  }

  @Test
  public void testMapGenerationWithExtremeDimensions() {
    MapGenerator generator = new MapGenerator();
    MapData smallMap = generator.generate(10, 10, 0);
    assertNotNull(smallMap);

    MapData hugeMap = generator.generate(10000, 10000, 999);
    assertNotNull(hugeMap);
  }
}
