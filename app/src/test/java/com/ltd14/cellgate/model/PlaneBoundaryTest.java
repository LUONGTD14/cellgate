package com.ltd14.cellgate.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlaneBoundaryTest {
  @Test
  public void testPlaneStaysNearTarget() {
    float initialX = 540f;
    float targetX = 1000f;
    Plane plane = new Plane(initialX, 1500f, 80f, 80f);

    plane.setTargetX(targetX);

    // Update many times to reach target
    for (int i = 0; i < 100; i++) {
      plane.update();
    }

    // Should be very close to target
    assertTrue("Plane should reach near targetX", Math.abs(plane.getX() - targetX) < 1.0f);
  }

  @Test
  public void testPlaneMovementLimits() {
    // Even if we set a crazy target, the plane should move smoothly
    Plane plane = new Plane(500f, 1000f, 80f, 80f);
    plane.setTargetX(5000f);
    plane.update();

    // dx = 4500, x += 4500 * 0.18 = 810. New x = 1310.
    assertTrue("Plane should move towards large target", plane.getX() > 500f);
    assertTrue("Plane angle should be capped", plane.getAngle() == 25f);
  }
}
