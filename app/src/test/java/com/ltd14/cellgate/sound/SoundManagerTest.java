package com.ltd14.cellgate.sound;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34)
public class SoundManagerTest {

  @Test
  public void testSoundManagerInitialization() {
    Context context = RuntimeEnvironment.getApplication();
    
    SoundManager soundManager = new SoundManager(context);
    
    soundManager.playSuccess();
    soundManager.playFail();
    soundManager.release();
  }
}
