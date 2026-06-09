package com.ltd14.cellgate.ui;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

public class HudRendererTest {

  private Context context;

  @Before
  public void setUp() {
    context = mock(Context.class);
    // Giả lập chuỗi resource
    when(context.getString(anyInt())).thenReturn("Score : ");
  }

  @Test
  public void testHudRendererInitialization() {
    // Kiểm tra xem việc khởi tạo có lỗi không
    HudRenderer hudRenderer = new HudRenderer(context);

    // Giả lập việc vẽ (với isReturnDefaultValues = true, Canvas sẽ không crash)
    // Lưu ý: Chúng ta không kiểm tra pixel thực tế trong Unit Test,
    // chỉ đảm bảo logic code không gây ngoại lệ.
  }
}
