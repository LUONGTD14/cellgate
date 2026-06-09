package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.ltd14.cellgate.model.MapData;
import com.ltd14.cellgate.model.Wall;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.Test;

public class MapGeneratorPoolTest {

  @Test
  @SuppressWarnings("unchecked")
  public void testWallObjectReuse() throws Exception {
    MapGenerator generator = new MapGenerator();

    MapData map1 = generator.generate(1080, 1920, 0);
    int initialWallCount = map1.getWalls().size();

    Field field = MapGenerator.class.getDeclaredField("wallPool");
    field.setAccessible(true);
    List<Wall> pool = (List<Wall>) field.get(generator);

    assertEquals("Pool should contain generated walls", initialWallCount, pool.size());

    MapData map2 = generator.generate(1080, 1920, 10);

    if (map2.getWalls().size() <= initialWallCount) {
      assertEquals(
          "Pool size should remain constant if no new walls needed", initialWallCount, pool.size());
    }

    if (!map1.getWalls().isEmpty() && !map2.getWalls().isEmpty()) {
      Wall firstWallFirstGen = map1.getWalls().get(0);
      Wall firstWallSecondGen = map2.getWalls().get(0);
      assertSame(
          "Should reuse the same Wall object instance", firstWallFirstGen, firstWallSecondGen);
    }
  }
}
