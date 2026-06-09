package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertNotNull;

import com.ltd14.cellgate.model.MapData;
import org.junit.Test;

public class MapGeneratorBridgeTest {

  @Test
  public void testBridgeGenerationAtDifferentScores() {
    MapGenerator generator = new MapGenerator();

    MapData map0 = generator.generate(1080, 1920, 0);
    assertNotNull(map0);

    MapData map50 = generator.generate(1080, 1920, 50);
    assertNotNull(map50);

    MapData map1000 = generator.generate(1080, 1920, 1000);
    assertNotNull(map1000);
  }
}
