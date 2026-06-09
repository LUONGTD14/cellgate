package com.ltd14.cellgate.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class GameLoopTest {

  @Test
  public void testGameLoopStartAndStop() throws InterruptedException {
    Runnable mockUpdate = mock(Runnable.class);
    GameLoop gameLoop = new GameLoop(mockUpdate);

    gameLoop.startLoop();
    assertTrue("GameLoop should be running", gameLoop.isAlive());

    Thread.sleep(100);

    gameLoop.stopLoop();
    gameLoop.join(500); // Wait for thread to finish

    assertFalse("GameLoop should have stopped", gameLoop.isAlive());
    verify(mockUpdate, atLeastOnce()).run();
  }
}
