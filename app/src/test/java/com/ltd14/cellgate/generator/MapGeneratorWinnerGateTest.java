package com.ltd14.cellgate.generator;

import static com.ltd14.cellgate.util.Constants.COLS;
import static org.junit.Assert.assertTrue;

import com.ltd14.cellgate.model.MapData;
import com.ltd14.cellgate.model.Wall;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class MapGeneratorWinnerGateTest {

  @Test
  public void testAtLeastOneColumnIsEmpty() {
    MapGenerator generator = new MapGenerator();
    int width = 1080;
    int height = 1920;

    for (int i = 0; i < 100; i++) {
      MapData mapData = generator.generate(width, height, i);

      Set<Integer> columnsWithWalls = new HashSet<>();
      float cellW = width / (float) COLS;

      for (Wall wall : mapData.getWalls()) {
        int col = (int) (wall.getRect().centerX() / cellW);
        columnsWithWalls.add(col);
      }

      boolean hasEmptyGate = false;
      int[] gateCols = {1, 3, 5, 7, 9};
      for (int col : gateCols) {
        if (!columnsWithWalls.contains(col)) {
          hasEmptyGate = true;
          break;
        }
      }

      assertTrue(
          "Map #" + i + " must have at least one completely empty gate column", hasEmptyGate);
    }
  }
}
