package com.ltd14.cellgate.ui;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

public class BackgroundRenderer {

  private Paint bgPaint;
  private int cachedHeight = -1;

  public void draw(Canvas canvas, int width, int height) {
    if (bgPaint == null || height != cachedHeight) {
      cachedHeight = height;
      bgPaint = new Paint();
      bgPaint.setShader(
          new LinearGradient(0, 0, 0, height, 0xFF0B1026, 0xFF181F4A, Shader.TileMode.CLAMP));
    }
    canvas.drawRect(0, 0, width, height, bgPaint);
  }
}
