package com.ltd14.cellgate.generator;

import static org.junit.Assert.assertTrue;

import com.ltd14.cellgate.model.MapData;
import org.junit.Test;

public class MapDifficultyTest {

  @Test
  public void testDifficultyIncreasesWithScore() {
    MapGenerator generator = new MapGenerator();
    int width = 1080;
    int height = 1920;

    MapData easyMap = generator.generate(width, height, 0);
    int easyWallCount = easyMap.getWalls().size();

    MapData hardMap = generator.generate(width, height, 1000);
    int hardWallCount = hardMap.getWalls().size();

    assertTrue(
        "Hard map should generally have more walls than easy map. Easy: "
            + easyWallCount
            + ", Hard: "
            + hardWallCount,
        hardWallCount >= easyWallCount);
  }
}
