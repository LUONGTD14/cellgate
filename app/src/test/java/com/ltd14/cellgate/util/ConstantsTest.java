package com.ltd14.cellgate.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConstantsTest {
  @Test
  public void verifyGameBalanceConstants() {
    // Ensure that critical game balance parameters are not accidentally changed
    assertEquals(11, Constants.COLS);
    assertEquals(21, Constants.MAX_ROWS);
    assertEquals(0.85f, Constants.HITBOX_RATIO, 0.001f);
    assertEquals(50, Constants.FPS);
  }
}
