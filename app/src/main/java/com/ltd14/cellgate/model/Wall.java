package com.ltd14.cellgate.model;

import android.graphics.RectF;

public class Wall {
  private final RectF rect;

  public Wall(float left, float top, float right, float bottom) {
    rect = new RectF(left, top, right, bottom);
  }

  public RectF getRect() {
    return rect;
  }
}
