package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertNotEquals;

import com.ltd14.cellgate.model.MapData;
import org.junit.Test;

public class MapGeneratorRandomnessTest {

  @Test
  public void testConsecutiveMapsAreDifferent() {
    MapGenerator generator = new MapGenerator();
    int width = 1080;
    int height = 1920;

    MapData map1 = generator.generate(width, height, 0);
    MapData map2 = generator.generate(width, height, 0);

    assertNotEquals(
        "Consecutive maps should be randomly different",
        map1.getWalls().size() + map1.getWalls().get(0).getRect().left,
        map2.getWalls().size() + map2.getWalls().get(0).getRect().left);
  }
}
