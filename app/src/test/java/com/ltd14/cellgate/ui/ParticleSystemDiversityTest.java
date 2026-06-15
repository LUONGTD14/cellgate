package com.ltd14.cellgate.ui;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.lang.reflect.Field;

public class ParticleSystemDiversityTest {

  @Test
  public void testParticleDiversity() throws Exception {
    int width = 1000;
    int height = 1000;
    ParticleSystem system = new ParticleSystem(width, height);

    Field field = ParticleSystem.class.getDeclaredField("particles");
    field.setAccessible(true);
    Object[] particles = (Object[]) field.get(system);

    assertTrue("Should have many particles", particles.length > 1);

    Class<?> particleClass = Class.forName("com.ltd14.cellgate.ui.ParticleSystem$Particle");
    Field xField = particleClass.getDeclaredField("x");
    Field speedField = particleClass.getDeclaredField("speed");
    xField.setAccessible(true);
    speedField.setAccessible(true);

    float firstX = (float) xField.get(particles[0]);
    float secondX = (float) xField.get(particles[1]);
    float firstSpeed = (float) speedField.get(particles[0]);

    // Probability of two particles having exactly the same X or speed is very low
    assertNotEquals("Particles should have different X positions", firstX, secondX, 0.001f);
    assertTrue("Speed should be within range", firstSpeed >= 1.0f && firstSpeed <= 3.0f);
  }
}
