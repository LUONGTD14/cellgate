package com.ltd14.cellgate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ltd14.cellgate.R;
import com.ltd14.cellgate.util.PreferenceUtil;

public class MainActivity extends AppCompatActivity {

  private TextView tvBest;
  private ImageButton btnPlay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    setContentView(R.layout.activity_main);

    tvBest = findViewById(R.id.tvBest);
    btnPlay = findViewById(R.id.btnPlay);

    refreshBestScore();

    btnPlay.setOnClickListener(
        v -> {
          startActivity(new Intent(MainActivity.this, GameActivity.class));
        });
  }

  @Override
  protected void onResume() {
    super.onResume();
    refreshBestScore();
  }

  private void refreshBestScore() {
    int best = PreferenceUtil.getBestScore(this);
    tvBest.setText("Best Score : " + best);
  }
}
