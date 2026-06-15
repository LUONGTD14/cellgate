package com.ltd14.cellgate.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.graphics.Canvas;
import android.graphics.Paint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34)
public class BackgroundRendererTest {

  @Test
  public void testDrawCallsCanvasDrawRect() {
    BackgroundRenderer renderer = new BackgroundRenderer();
    Canvas mockCanvas = mock(Canvas.class);

    int width = 1080;
    int height = 1920;

    renderer.draw(mockCanvas, width, height);

    verify(mockCanvas)
        .drawRect(eq(0f), eq(0f), eq((float) width), eq((float) height), any(Paint.class));
  }

  @Test
  public void testCachingLogic() {
    BackgroundRenderer renderer = new BackgroundRenderer();
    Canvas mockCanvas = mock(Canvas.class);

    renderer.draw(mockCanvas, 1080, 1920);
    renderer.draw(mockCanvas, 1080, 1920);

    verify(mockCanvas, atLeastOnce())
        .drawRect(eq(0f), eq(0f), eq(1080f), eq(1920f), any(Paint.class));
  }
}
