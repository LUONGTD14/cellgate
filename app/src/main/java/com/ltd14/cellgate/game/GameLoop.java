package com.ltd14.cellgate.game;

import static com.ltd14.cellgate.util.Constants.FPS;

public class GameLoop extends Thread {

  private final Runnable updateRunnable;
  private boolean running;

  public GameLoop(Runnable updateRunnable) {
    this.updateRunnable = updateRunnable;
  }

  @Override
  public void run() {
    long frameTime = 1000 / FPS;
    while (running) {
      long start = System.currentTimeMillis();
      updateRunnable.run();

      long elapsed = System.currentTimeMillis() - start;
      long sleep = frameTime - elapsed;
      if (sleep > 0) {
        try {
          Thread.sleep(sleep);
        } catch (Exception ignored) {
        }
      }
    }
  }

  public void startLoop() {
    running = true;
    start();
  }

  public void stopLoop() {
    running = false;
  }
}
