package com.ltd14.cellgate.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlaneTest {

  @Test
  public void testPlaneMovementTowardsTarget() {
    float initialX = 500f;
    float targetX = 600f;
    Plane plane = new Plane(initialX, 1000f, 80f, 80f);

    plane.setTargetX(targetX);
    plane.update();

    assertTrue("Plane should move towards target", plane.getX() > initialX);
    assertTrue("Plane should not exceed target", plane.getX() < targetX);
  }

  @Test
  public void testPlaneAngleLimits() {
    Plane plane = new Plane(500f, 1000f, 80f, 80f);

    plane.setTargetX(2000f);
    plane.update();

    assertTrue("Angle should be capped at 25", plane.getAngle() <= 25f);

    plane.setTargetX(-1000f);
    plane.update();
    assertTrue("Angle should be capped at -25", plane.getAngle() >= -25f);
  }

  @Test
  public void testSetPosition() {
    Plane plane = new Plane(100f, 100f, 50f, 50f);
    plane.setPosition(300f, 400f);

    assertEquals(300f, plane.getX(), 0.01f);
    assertEquals(400f, plane.getY(), 0.01f);
  }
}
