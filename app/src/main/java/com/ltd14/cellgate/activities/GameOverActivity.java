package com.ltd14.cellgate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ltd14.cellgate.R;
import com.ltd14.cellgate.util.PreferenceUtil;

public class GameOverActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_over);

    TextView tvScore = findViewById(R.id.tvScore);
    TextView tvBest = findViewById(R.id.tvBest);
    ImageButton btnRetry = findViewById(R.id.btnRetry);
    ImageButton btnHome = findViewById(R.id.btnHome);

    int score = getIntent().getIntExtra("score", 0);
    tvScore.setText("Score : " + score);
    tvBest.setText("Best : " + PreferenceUtil.getBestScore(this));

    btnRetry.setOnClickListener(
        v -> {
          Intent intent = new Intent(this, GameActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
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

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
  }
}
