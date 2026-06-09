package com.ltd14.cellgate.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.SharedPreferences;
import org.junit.Before;
import org.junit.Test;

public class PreferenceUtilTest {

  private Context context;
  private SharedPreferences sharedPreferences;
  private SharedPreferences.Editor editor;

  @Before
  public void setUp() {
    context = mock(Context.class);
    sharedPreferences = mock(SharedPreferences.class);
    editor = mock(SharedPreferences.Editor.class);

    when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences);
    when(sharedPreferences.edit()).thenReturn(editor);
    when(editor.putInt(anyString(), anyInt())).thenReturn(editor);
  }

  @Test
  public void testSaveBestScore() {
    int scoreToSave = 100;
    PreferenceUtil.saveBestScore(context, scoreToSave);

    verify(sharedPreferences).edit();
    verify(editor).putInt(eq(Constants.BEST_SCORE), eq(scoreToSave));
    verify(editor).apply();
  }

  @Test
  public void testGetBestScore() {
    int expectedScore = 50;
    when(sharedPreferences.getInt(eq(Constants.BEST_SCORE), anyInt())).thenReturn(expectedScore);

    int actualScore = PreferenceUtil.getBestScore(context);

    assertEquals(expectedScore, actualScore);
    verify(sharedPreferences).getInt(eq(Constants.BEST_SCORE), eq(0));
  }
}
