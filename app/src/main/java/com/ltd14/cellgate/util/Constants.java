package com.ltd14.cellgate.util;

public final class Constants {
  public static final int FPS = 60;
  public static final float PLANE_WIDTH = 80f;
  public static final float PLANE_HEIGHT = 80f;
  
  // Hitbox reduction ratio (10%)
  public static final float HITBOX_RATIO = 0.9f;

  public static final int COLS = 11;
  public static final int MAX_ROWS = 21;
  public static final int ROWS_FOR_CELLH = 19;
  
  public static final float WALL_WIDTH_RATIO = 0.75f;
  public static final float WALL_HEIGHT_RATIO = 0.85f;
  public static final float MAX_WALL_OF_GATE_RATIO = 0.85f;
  public static final float MAX_WALL_RATIO = 0.6f;
  
  // Map Generation Constants
  public static final int BASE_BRIDGE_COUNT = 3;
  public static final int SCORE_PER_BRIDGE = 5;
  public static final int MAX_CONSECUTIVE_EMPTY = 4;
  public static final float DIFFICULTY_INCREMENT = 0.01f;
  public static final float MIN_KEEP_RATIO = 0.4f;

  public static final String PREF_NAME = "sky_escape_pref";
  public static final String BEST_SCORE = "best_score";

  private Constants() {}
}
