package com.ltd14.cellgate.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ScoreManagerTest {

  private ScoreManager scoreManager;

  @Before
  public void setUp() {
    scoreManager = new ScoreManager();
  }

  @Test
  public void testInitialScoreIsZero() {
    assertEquals(0, scoreManager.getScore());
  }

  @Test
  public void testIncreaseScore() {
    scoreManager.increase();
    assertEquals(1, scoreManager.getScore());
    scoreManager.increase();
    assertEquals(2, scoreManager.getScore());
  }

  @Test
  public void testResetScore() {
    scoreManager.increase();
    scoreManager.increase();
    scoreManager.reset();
    assertEquals(0, scoreManager.getScore());
  }
}
