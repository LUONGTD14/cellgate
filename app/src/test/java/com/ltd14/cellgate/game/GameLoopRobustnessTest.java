package com.ltd14.cellgate.game;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class GameLoopRobustnessTest {

  @Test
  public void testGameLoopContinuesOnException() throws InterruptedException {
    Runnable faultyRunnable = mock(Runnable.class);
    doThrow(new RuntimeException("Simulated crash")).when(faultyRunnable).run();

    GameLoop gameLoop = new GameLoop(faultyRunnable);
    gameLoop.startLoop();

    Thread.sleep(100);

    assertTrue(
        "GameLoop should still be alive even if update throws exception", gameLoop.isAlive());

    gameLoop.stopLoop();
    gameLoop.join(500);
  }
}
