package com.ltd14.cellgate.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlaneUpdateTest {
  @Test
  public void testPlaneNoMovementWhenAtTarget() {
    Plane plane = new Plane(500f, 1000f, 80f, 80f);
    plane.update(); // targetX defaults to initial x
    assertEquals(500f, plane.getX(), 0.001f);
    assertEquals(0f, plane.getAngle(), 0.001f);
  }

  @Test
  public void testLaneManagement() {
    Plane plane = new Plane(500f, 1000f, 80f, 80f);
    plane.setLane(3);
    assertEquals(3, plane.getLane());
  }
}
