package com.ltd14.cellgate.game;

public class ScoreManager {
  private int score;

  public void reset() {
    score = 0;
  }

  public void increase() {
    score++;
  }

  public int getScore() {
    return score;
  }
}
