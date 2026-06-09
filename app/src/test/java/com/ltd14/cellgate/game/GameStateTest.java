package com.ltd14.cellgate.game;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameStateTest {
  @Test
  public void testGameStateEnum() {
    assertEquals("READY", GameState.READY.name());
    assertEquals("PLAYING", GameState.PLAYING.name());
    assertEquals("PAUSED", GameState.PAUSED.name());
    assertEquals("GAME_OVER", GameState.GAME_OVER.name());
  }
}
