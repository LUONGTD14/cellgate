package com.ltd14.cellgate.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

public class BackgroundRenderer {

  private Bitmap bgBitmap;
  private int cachedWidth = -1;
  private int cachedHeight = -1;

  public void draw(Canvas canvas, int width, int height) {
    if (bgBitmap == null || width != cachedWidth || height != cachedHeight) {
      cachedWidth = width;
      cachedHeight = height;
      
      if (bgBitmap != null) {
        bgBitmap.recycle();
      }
      
      bgBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
      Canvas tempCanvas = new Canvas(bgBitmap);
      Paint tempPaint = new Paint();
      tempPaint.setShader(
          new LinearGradient(0, 0, 0, height, 0xFF0B1026, 0xFF181F4A, Shader.TileMode.CLAMP));
      tempCanvas.drawRect(0, 0, width, height, tempPaint);
    }
    canvas.drawBitmap(bgBitmap, 0, 0, null);
  }
}
