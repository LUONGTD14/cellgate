package com.ltd14.cellgate.model;

import android.graphics.RectF;

public class Plane {
  private final float width;
  private final float height;
  private float x;
  private float y;
  private float targetX;
  private float angle;
  private int lane;
  private final RectF bounds = new RectF();

  public Plane(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.targetX = x;
    updateBounds();
  }

  public void update() {
    float dx = targetX - x;
    x += dx * 0.18f;
    angle = dx * 0.08f;
    if (angle > 25) angle = 25;
    if (angle < -25) angle = -25;
    updateBounds();
  }

  private void updateBounds() {
    bounds.set(x - width / 2f, y - height / 2f, x + width / 2f, y + height / 2f);
  }

  public RectF getBounds() {
    return bounds;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public float getAngle() {
    return angle;
  }

  public void setTargetX(float targetX) {
    this.targetX = targetX;
  }

  public void setPosition(float x, float y) {
    this.x = x;
    this.y = y;
    this.targetX = x;
    updateBounds();
  }

  public int getLane() {
    return lane;
  }

  public void setLane(int lane) {
    this.lane = lane;
  }
}
