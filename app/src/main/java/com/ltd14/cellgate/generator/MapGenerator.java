package com.ltd14.cellgate.generator;

import static com.ltd14.cellgate.util.Constants.COLS;
import static com.ltd14.cellgate.util.Constants.MAX_ROWS;
import static com.ltd14.cellgate.util.Constants.MAX_WALL_OF_GATE_RATIO;
import static com.ltd14.cellgate.util.Constants.MAX_WALL_RATIO;
import static com.ltd14.cellgate.util.Constants.ROWS_FOR_CELLH;
import static com.ltd14.cellgate.util.Constants.WALL_HEIGHT_RATIO;
import static com.ltd14.cellgate.util.Constants.WALL_WIDTH_RATIO;

import com.ltd14.cellgate.model.MapData;
import com.ltd14.cellgate.model.Wall;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapGenerator {
  private static final int[] GATE_COLS = {1, 3, 5, 7, 9};
  private static final int GATE_COUNT = GATE_COLS.length;
  private static final int[] OPEN_BOTTOM_OPTIONS = {7, 10, 11, 15, 19};
  private final Random random = new Random();

  public MapData generate(int width, int height, int score) {
    MapData map = new MapData();

    int rows = MAX_ROWS;
    float cellW = width / (float) COLS;
    float cellH = height / (float) ROWS_FOR_CELLH;
    float wallWidth = cellW * WALL_WIDTH_RATIO;
    float wallHeight = cellH * WALL_HEIGHT_RATIO;
    float marginX = (cellW - wallWidth) / 2f;
    float marginY = (cellH - wallHeight) / 2f;

    float mapHeight = rows * cellH; // 21 * (height/19) > height

    boolean[][] wall = new boolean[rows][COLS];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < COLS; c++) {
        wall[r][c] = true;
      }
    }

    int winnerIndex = random.nextInt(GATE_COUNT);
    int winnerCol = GATE_COLS[winnerIndex];

    Map<Integer, Integer> openBottomCount = new HashMap<>();
    for (int gateCol : GATE_COLS) {
      if (gateCol == winnerCol) {
        openBottomCount.put(gateCol, rows);
      } else {
        int open = OPEN_BOTTOM_OPTIONS[random.nextInt(OPEN_BOTTOM_OPTIONS.length)];
        if (open > rows) open = rows;
        openBottomCount.put(gateCol, open);
      }
    }

    for (int gateCol : GATE_COLS) {
      int openCount = openBottomCount.get(gateCol);
      int startRow = rows - openCount;
      for (int r = startRow; r < rows; r++) {
        wall[r][gateCol] = false;
      }
    }

    int bridgeCount = 3 + score / 5;
    if (bridgeCount > 10) bridgeCount = 10;
    for (int b = 0; b < bridgeCount; b++) {
      int idx1 = random.nextInt(GATE_COUNT);
      int idx2 = random.nextInt(GATE_COUNT);
      while (idx2 == idx1) idx2 = random.nextInt(GATE_COUNT);
      int col1 = GATE_COLS[idx1];
      int col2 = GATE_COLS[idx2];
      int startCol = Math.min(col1, col2);
      int endCol = Math.max(col1, col2);
      int row = random.nextInt(rows);
      for (int c = startCol; c <= endCol; c++) {
        wall[row][c] = false;
      }
    }

    float baseRatio = 0.4f + score * 0.01f;
    if (baseRatio > MAX_WALL_OF_GATE_RATIO) baseRatio = MAX_WALL_OF_GATE_RATIO;
    for (int c = 0; c < COLS; c++) {
      if (c % 2 == 0) {
        float maxRatio = (c == 0 || c == 10) ? MAX_WALL_OF_GATE_RATIO : MAX_WALL_RATIO;
        float keepRatio = Math.min(baseRatio, maxRatio);
        for (int r = 0; r < rows; r++) {
          if (wall[r][c] && random.nextFloat() > keepRatio) {
            wall[r][c] = false;
          }
        }
      }
    }

    for (int c = 0; c < COLS; c += 2) {
      int consecutiveEmpty = 0;
      for (int r = 0; r < rows; r++) {
        if (!wall[r][c]) {
          consecutiveEmpty++;
          if (consecutiveEmpty >= 4) {
            int fixRow = r - 2;
            if (fixRow >= 0) wall[fixRow][c] = true;
            consecutiveEmpty = 0;
          }
        } else {
          consecutiveEmpty = 0;
        }
      }
    }

    for (int c = 0; c < COLS; c += 2) {
      wall[0][c] = true;
      wall[20][c] = true;
    }

    for (int r = 0; r < rows; r++) {
      float topBase = r * cellH;
      float wallTop = topBase + marginY;
      float wallBottom = wallTop + wallHeight;
      for (int c = 0; c < COLS; c++) {
        if (!wall[r][c]) continue;
        float leftBase = c * cellW;
        float left = leftBase + marginX;
        float right = left + wallWidth;
        map.addWall(new Wall(left, wallTop, right, wallBottom));
      }
    }

    map.setMapHeight(mapHeight);
    return map;
  }
}
