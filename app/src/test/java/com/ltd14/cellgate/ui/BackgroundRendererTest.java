package com.ltd14.cellgate.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.graphics.Bitmap;
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
    public void testDrawCallsCanvasDrawBitmap() {
        BackgroundRenderer renderer = new BackgroundRenderer();
        Canvas mockCanvas = mock(Canvas.class);

        int width = 1080;
        int height = 1920;

        renderer.draw(mockCanvas, width, height);

        verify(mockCanvas).drawBitmap(any(Bitmap.class), eq(0f), eq(0f), isNull());
    }

    @Test
    public void testCachingLogic() {
        BackgroundRenderer renderer = new BackgroundRenderer();
        Canvas mockCanvas = mock(Canvas.class);

        renderer.draw(mockCanvas, 1080, 1920);
        renderer.draw(mockCanvas, 1080, 1920);

        verify(mockCanvas, atLeastOnce()).drawBitmap(any(Bitmap.class), eq(0f), eq(0f), isNull());
    }
}
