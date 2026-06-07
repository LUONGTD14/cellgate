package com.ltd14.cellgate.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

  private static SharedPreferences getPref(Context context) {
    return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
  }

  public static void saveBestScore(Context context, int score) {
    getPref(context).edit().putInt(Constants.BEST_SCORE, score).apply();
  }

  public static int getBestScore(Context context) {
    return getPref(context).getInt(Constants.BEST_SCORE, 0);
  }
}
