package com.ltd14.cellgate.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class HudRenderer {

  private final Paint labelPaint = new Paint();
  private final Paint scorePaint = new Paint();

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
    String label = "Score : ";
    String scoreText = String.valueOf(score);
    float x = 40;
    float y = 160;

    canvas.drawText(label, x, y, labelPaint);

    float labelWidth = labelPaint.measureText(label);
    canvas.drawText(scoreText, x + labelWidth, y, scorePaint);
  }
}
