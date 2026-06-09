package com.ltd14.cellgate.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import com.ltd14.cellgate.R;

public class HudRenderer {

  private final Paint labelPaint = new Paint();
  private final Paint scorePaint = new Paint();
  private final String label;
  private int lastScore = -1;
  private String scoreText = "0";
  private float labelWidth = -1f;

  public HudRenderer(Context context) {
    this.label = context.getString(R.string.score_label);
    
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
    
    float x = 46;
    float y = 160;

    canvas.drawText(label, x, y, labelPaint);
    canvas.drawText(scoreText, x + labelWidth, y, scorePaint);
  }
}
