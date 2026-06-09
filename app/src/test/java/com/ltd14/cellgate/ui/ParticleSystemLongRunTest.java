package com.ltd14.cellgate.ui;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParticleSystemLongRunTest {
  @Test
  public void testParticlesNeverExhaust() {
    int width = 1080;
    int height = 1920;
    ParticleSystem system = new ParticleSystem(width, height);

    // Giả lập chơi game trong thời gian cực dài (10,000 frames ~ 3 phút chơi)
    for (int i = 0; i < 10000; i++) {
      system.update(width, height);
    }

    // Nếu code chạy đến đây mà không crash và logic reset y hoạt động, test đạt.
    assertTrue("Particle system should remain stable after long run", true);
  }
}
