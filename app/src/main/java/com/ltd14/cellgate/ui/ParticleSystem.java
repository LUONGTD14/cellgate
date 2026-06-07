package com.ltd14.cellgate.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

public class ParticleSystem {

  private final Particle[] particles;
  private final Random random = new Random();

  public ParticleSystem(int width, int height) {

    particles = new Particle[80];

    for (int i = 0; i < particles.length; i++) {

      Particle p = new Particle();

      p.x = random.nextInt(width);
      p.y = random.nextInt(height);
      p.radius = 2 + random.nextInt(6);
      p.speed = 1 + random.nextFloat() * 2;
      particles[i] = p;
    }
  }

  public void update(int width, int height) {
    for (Particle p : particles) {

      p.y += p.speed;

      if (p.y > height) {
        p.y = 0;
        p.x = random.nextInt(width);
      }
    }
  }

  public void draw(Canvas canvas, Paint paint) {
    for (Particle p : particles) {
      canvas.drawCircle(p.x, p.y, p.radius, paint);
    }
  }

  private static class Particle {
    float x;
    float y;
    float radius;
    float speed;
  }
}
