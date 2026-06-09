package com.ltd14.cellgate.activities;

import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.ltd14.cellgate.game.GameView;

public class GameActivity extends AppCompatActivity {

  private GameView gameView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    gameView = new GameView(this);
    setContentView(gameView);
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (gameView != null) {
      gameView.pause();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (gameView != null) {
      gameView.resume();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}
