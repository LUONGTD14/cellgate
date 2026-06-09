package com.ltd14.cellgate.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlaneAngleTest {
  @Test
  public void testAngleCalculations() {
    Plane plane = new Plane(500f, 1000f, 80f, 80f);

    // Moving right (dx positive -> angle positive)
    plane.setTargetX(600f);
    plane.update();
    assertTrue("Angle should be positive when moving right", plane.getAngle() > 0);

    // Moving left (dx negative -> angle negative)
    plane.setPosition(500f, 1000f);
    plane.setTargetX(400f);
    plane.update();
    assertTrue("Angle should be negative when moving left", plane.getAngle() < 0);

    // Stationary (dx = 0 -> angle = 0)
    plane.setPosition(500f, 1000f);
    plane.setTargetX(500f);
    plane.update();
    assertEquals(0f, plane.getAngle(), 0.001f);
  }
}
