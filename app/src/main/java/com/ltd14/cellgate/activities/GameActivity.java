package com.ltd14.cellgate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.ltd14.cellgate.R;
import com.ltd14.cellgate.game.GameState;
import com.ltd14.cellgate.game.GameView;

public class GameActivity extends AppCompatActivity {

  private GameView gameView;
  private View pauseMenu;
  private ImageButton btnPause;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    setContentView(R.layout.activity_game);

    FrameLayout container = findViewById(R.id.game_container);
    gameView = new GameView(this);
    container.addView(gameView);

    pauseMenu = findViewById(R.id.pauseMenu);
    btnPause = findViewById(R.id.btnPause);
    Button btnResume = findViewById(R.id.btnResume);
    Button btnHome = findViewById(R.id.btnHome);

    btnPause.setOnClickListener(v -> pauseGame());
    btnResume.setOnClickListener(v -> resumeGame());
    btnHome.setOnClickListener(v -> {
      Intent intent = new Intent(GameActivity.this, MainActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(intent);
      finish();
    });
  }

  private void pauseGame() {
    if (gameView != null && gameView.getGameState() == GameState.PLAYING) {
      gameView.setGameState(GameState.PAUSED);
      pauseMenu.setVisibility(View.VISIBLE);
      btnPause.setVisibility(View.GONE);
    }
  }

  private void resumeGame() {
    if (gameView != null && gameView.getGameState() == GameState.PAUSED) {
      gameView.setGameState(GameState.PLAYING);
      pauseMenu.setVisibility(View.GONE);
      btnPause.setVisibility(View.VISIBLE);
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    // Khi nhấn Home hoặc Power, Activity sẽ vào onPause
    // Ta tự động kích hoạt trạng thái Pause của Game
    pauseGame();
    if (gameView != null) {
      gameView.pause(); // Dừng thread game
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (gameView != null) {
      gameView.resume(); // Khởi động lại thread game
    }
  }

  @Override
  public void onBackPressed() {
    if (gameView != null && gameView.getGameState() == GameState.PLAYING) {
      pauseGame();
    } else {
      super.onBackPressed();
    }
  }
}
