package com.ltd14.cellgate.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class HudRenderer {

  private final Paint labelPaint = new Paint();
  private final Paint scorePaint = new Paint();
  private int lastScore = -1;
  private String scoreText = "0";
  private float labelWidth = -1f;
  private final String label = "Score : ";

  public HudRenderer() {
    labelPaint.setColor(Color.WHITE);
    labelPaint.setTextSize(60f);
    labelPaint.setAntiAlias(true);

    scorePaint.setColor(Color.RED);
    scorePaint.setTextSize(60f);
    scorePaint.setAntiAlias(true);
    scorePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
  }

  public void draw(Canvas canvas, int score) {
    if (labelWidth == -1f) {
      labelWidth = labelPaint.measureText(label);
    }
    
    if (score != lastScore) {
      lastScore = score;
      scoreText = String.valueOf(score);
    }
    
    float x = 40;
    float y = 160;

    canvas.drawText(label, x, y, labelPaint);
    canvas.drawText(scoreText, x + labelWidth, y, scorePaint);
  }
}
