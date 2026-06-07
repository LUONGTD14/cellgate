package com.ltd14.cellgate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ltd14.cellgate.R;
import com.ltd14.cellgate.util.PreferenceUtil;

public class GameOverActivity extends AppCompatActivity {

  private TextView tvScore, tvBest;
  private Button btnRetry, btnHome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    setContentView(R.layout.activity_game_over);

    tvScore = findViewById(R.id.tvScore);
    tvBest = findViewById(R.id.tvBest);
    btnRetry = findViewById(R.id.btnRetry);
    btnHome = findViewById(R.id.btnHome);

    int score = getIntent().getIntExtra("score", 0);
    int best = PreferenceUtil.getBestScore(this);

    tvScore.setText("Score : " + score);
    tvBest.setText("Best : " + best);

    btnRetry.setOnClickListener(
        v -> {
          startActivity(new Intent(this, GameActivity.class));
          finish();
        });

    btnHome.setOnClickListener(
        v -> {
          Intent intent = new Intent(this, MainActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(intent);
          finish();
        });
  }
}
