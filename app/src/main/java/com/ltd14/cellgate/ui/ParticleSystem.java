package com.ltd14.cellgate.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

public class ParticleSystem {

  private final Particle[] particles;
  private final float[] drawBuffer;
  private final Random random = new Random();

  public ParticleSystem(int width, int height) {
    particles = new Particle[40];
    drawBuffer = new float[particles.length * 2]; // [x1, y1, x2, y2, ...]

    for (int i = 0; i < particles.length; i++) {
      Particle p = new Particle();
      p.x = random.nextInt(width);
      p.y = random.nextInt(height);
      p.speed = 1 + random.nextFloat() * 2;
      particles[i] = p;
    }
  }

  public void update(int width, int height) {
    for (int i = 0; i < particles.length; i++) {
      Particle p = particles[i];
      p.y += p.speed;
      if (p.y > height) {
        p.y = 0;
        p.x = random.nextInt(width);
      }
      // Fill buffer for drawing
      drawBuffer[i * 2] = p.x;
      drawBuffer[i * 2 + 1] = p.y;
    }
  }

  public void draw(Canvas canvas, Paint paint) {
    // Vẽ toàn bộ 40 hạt chỉ với 1 Draw Call
    paint.setStrokeWidth(6); // Độ lớn của hạt
    paint.setStrokeCap(Paint.Cap.ROUND);
    canvas.drawPoints(drawBuffer, paint);
  }

  private static class Particle {
    float x;
    float y;
    float speed;
  }
}
