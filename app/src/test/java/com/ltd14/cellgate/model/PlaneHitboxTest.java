package com.ltd14.cellgate.model;

import static com.ltd14.cellgate.util.Constants.HITBOX_RATIO;
import static org.junit.Assert.assertEquals;

import android.graphics.RectF;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34) // Ép Robolectric chạy trên SDK 34 để hỗ trợ RectF và AudioAttributes
public class PlaneHitboxTest {

  @Test
  public void testHitboxReduction() {
    float width = 100f;
    float height = 100f;
    float x = 500f;
    float y = 500f;

    Plane plane = new Plane(x, y, width, height);
    RectF bounds = plane.getBounds();

    float expectedHalfWidth = (width * HITBOX_RATIO) / 2f;
    float expectedHalfHeight = (height * HITBOX_RATIO) / 2f;

    // Khi có @Config(sdk = 34), RectF sẽ hoạt động đúng và trả về kết quả tính toán thực tế
    assertEquals(x - expectedHalfWidth, bounds.left, 0.01f);
    assertEquals(x + expectedHalfWidth, bounds.right, 0.01f);
    assertEquals(y - expectedHalfHeight, bounds.top, 0.01f);
    assertEquals(y + expectedHalfHeight, bounds.bottom, 0.01f);
  }
}
