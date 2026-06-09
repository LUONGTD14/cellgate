package com.ltd14.cellgate.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.graphics.Canvas;
import android.graphics.Paint;

import org.junit.Test;

public class BackgroundRendererTest {

  @Test
  public void testDrawCallsCanvasDrawRect() {
    BackgroundRenderer renderer = new BackgroundRenderer();
    Canvas mockCanvas = mock(Canvas.class);

    int width = 1080;
    int height = 1920;

    renderer.draw(mockCanvas, width, height);

    // Verify that drawRect was called with the correct dimensions
    verify(mockCanvas)
        .drawRect(eq(0f), eq(0f), eq((float) width), eq((float) height), any(Paint.class));
  }

  @Test
  public void testCachingLogic() {
    BackgroundRenderer renderer = new BackgroundRenderer();
    Canvas mockCanvas = mock(Canvas.class);

    // Draw once
    renderer.draw(mockCanvas, 1080, 1920);
    // Draw again with same dimensions - should use cached shader (no crash/error)
    renderer.draw(mockCanvas, 1080, 1920);

    verify(mockCanvas, atLeastOnce())
        .drawRect(anyFloat(), anyFloat(), anyFloat(), anyFloat(), any(Paint.class));
  }
}
