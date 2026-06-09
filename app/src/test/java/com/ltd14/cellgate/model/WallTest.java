package com.ltd14.cellgate.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class WallTest {
  @Test
  public void testWallCreation() {
    Wall wall = new Wall(10, 20, 30, 40);
    assertNotNull(wall.getRect());
  }
}
