package com.ltd14.cellgate.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import org.junit.Test;

public class ParticleSystemMovementTest {

  @Test
  public void testParticlesMoveDown() throws Exception {
    int width = 1000;
    int height = 1000;
    ParticleSystem system = new ParticleSystem(width, height);

    // Get particles via reflection to check positions
    Field field = ParticleSystem.class.getDeclaredField("particles");
    field.setAccessible(true);
    Object[] particles = (Object[]) field.get(system);

    Class<?> particleClass = Class.forName("com.ltd14.cellgate.ui.ParticleSystem$Particle");
    Field yField = particleClass.getDeclaredField("y");
    yField.setAccessible(true);

    float initialY = (float) yField.get(particles[0]);

    system.update(width, height);

    float updatedY = (float) yField.get(particles[0]);
    assertTrue("Particle should move down", updatedY > initialY || updatedY == 0);
  }

  @Test
  public void testParticlesResetWhenOffScreen() throws Exception {
    int width = 1000;
    int height = 1000;
    ParticleSystem system = new ParticleSystem(width, height);

    Field field = ParticleSystem.class.getDeclaredField("particles");
    field.setAccessible(true);
    Object[] particles = (Object[]) field.get(system);

    Class<?> particleClass = Class.forName("com.ltd14.cellgate.ui.ParticleSystem$Particle");
    Field yField = particleClass.getDeclaredField("y");
    Field xField = particleClass.getDeclaredField("x");
    yField.setAccessible(true);
    xField.setAccessible(true);

    // Force a particle to the bottom
    yField.set(particles[0], 1001f);
    float oldX = (float) xField.get(particles[0]);

    system.update(width, height);

    float newY = (float) yField.get(particles[0]);
    assertEquals("Particle should reset to y=0", 0f, newY, 0.001f);
  }
}
