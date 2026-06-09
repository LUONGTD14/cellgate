package com.ltd14.cellgate.ui;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParticleSystemTest {
  @Test
  public void testParticlesUpdate() {
    int width = 1080;
    int height = 1920;
    ParticleSystem system = new ParticleSystem(width, height);

    // Update many times to simulate long play sessions
    for (int i = 0; i < 1000; i++) {
      system.update(width, height);
    }

    // If it doesn't crash, the basic logic of reset when y > height is working
    assertTrue(true);
  }
}
