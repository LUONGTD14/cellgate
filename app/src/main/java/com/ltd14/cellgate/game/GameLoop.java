package com.ltd14.cellgate.game;

import static com.ltd14.cellgate.util.Constants.FPS;

import android.util.Log;

public class GameLoop extends Thread {
  private static final String TAG = "GameLoop";
  private final Runnable updateRunnable;
  private volatile boolean running;

  public GameLoop(Runnable updateRunnable) {
    this.updateRunnable = updateRunnable;
  }

  @Override
  public void run() {
    long targetTime = 1_000_000_000 / FPS; // nanoseconds
    while (running) {
      long startTime = System.nanoTime();

      try {
        updateRunnable.run();
      } catch (Exception e) {
        Log.e(TAG, "Error in game loop", e);
      }

      long timeTaken = System.nanoTime() - startTime;
      long sleepTime = (targetTime - timeTaken) / 1_000_000; // convert to milliseconds

      if (sleepTime > 0) {
        try {
          Thread.sleep(sleepTime);
        } catch (InterruptedException ignored) {
        }
      }
    }
  }

  public void startLoop() {
    running = true;
    if (!isAlive()) {
      start();
    }
  }

  public void stopLoop() {
    running = false;
  }
}
