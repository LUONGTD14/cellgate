package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertNotNull;

import com.ltd14.cellgate.model.MapData;
import org.junit.Test;

public class MapGeneratorWallFixTest {

  @Test
  public void testWallFixLogic() {
    MapGenerator generator = new MapGenerator();
    for (int i = 0; i < 20; i++) {
      MapData mapData = generator.generate(1080, 1920, i * 100);
      assertNotNull(mapData);
    }
  }
}
