package com.ltd14.cellgate.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.graphics.RectF;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34)
public class PlaneBoundsUpdateTest {

    @Test
    public void testBoundsUpdateAfterSetPosition() {
        Plane plane = new Plane(100, 100, 50, 50);
        RectF initialBounds = new RectF(plane.getBounds());
        
        plane.setPosition(500, 500);
        RectF updatedBounds = plane.getBounds();
        
        assertNotEquals("Bounds should change after setPosition", initialBounds, updatedBounds);
        assertEquals(500f, updatedBounds.centerX(), 0.01f);
        assertEquals(500f, updatedBounds.centerY(), 0.01f);
    }
    
    @Test
    public void testBoundsUpdateAfterUpdate() {
        Plane plane = new Plane(100, 100, 50, 50);
        RectF initialBounds = new RectF(plane.getBounds());
        
        plane.setTargetX(200);
        plane.update(); // Move towards target
        
        RectF updatedBounds = plane.getBounds();
        assertNotEquals("Bounds should change after update movement", initialBounds, updatedBounds);
    }
}
