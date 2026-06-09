package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ltd14.cellgate.model.MapData;
import org.junit.Before;
import org.junit.Test;

public class MapGeneratorTest {

  private MapGenerator mapGenerator;

  @Before
  public void setUp() {
    mapGenerator = new MapGenerator();
  }

  @Test
  public void testGenerateMapData() {
    int width = 1080;
    int height = 1920;
    int score = 0;

    MapData mapData = mapGenerator.generate(width, height, score);

    assertNotNull(mapData);
    assertTrue("Map should have walls", mapData.getWalls().size() > 0);

    float expectedCellH = height / 19f;
    float expectedMapHeight = 21 * expectedCellH;
    assertEquals(expectedMapHeight, mapData.getMapHeight(), 0.01f);
  }
}
