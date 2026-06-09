package com.ltd14.cellgate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.graphics.RectF;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.ltd14.cellgate.model.Plane;
import com.ltd14.cellgate.model.Wall;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PlaneCollisionTest {

  @Test
  public void testPlaneAndWallCollision() {
    Plane plane = new Plane(500, 500, 100, 100);

    Wall wallHit = new Wall(480, 480, 520, 520);
    assertTrue("Should detect collision", RectF.intersects(plane.getBounds(), wallHit.getRect()));

    Wall wallMiss = new Wall(0, 0, 100, 100);
    assertFalse(
        "Should not detect collision", RectF.intersects(plane.getBounds(), wallMiss.getRect()));
  }
}
