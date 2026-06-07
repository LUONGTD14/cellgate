package com.ltd14.cellgate.sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import com.ltd14.cellgate.R;

public class SoundManager {
  private final SoundPool soundPool;
  private final int successSound;
  private final int failSound;

  public SoundManager(Context context) {

    AudioAttributes attr =
        new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).build();

    soundPool = new SoundPool.Builder().setMaxStreams(5).setAudioAttributes(attr).build();
    successSound = soundPool.load(context, R.raw.success, 1);
    failSound = soundPool.load(context, R.raw.fail, 1);
  }

  public void playSuccess() {
    soundPool.play(successSound, 1, 1, 1, 0, 1);
  }

  public void playFail() {
    soundPool.play(failSound, 1, 1, 1, 0, 1);
  }

  public void release() {
    soundPool.release();
  }
}
