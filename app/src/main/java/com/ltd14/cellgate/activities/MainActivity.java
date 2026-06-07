package com.ltd14.cellgate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ltd14.cellgate.R;
import com.ltd14.cellgate.util.PreferenceUtil;

public class MainActivity extends AppCompatActivity {

  private TextView tvBest;
  private Button btnPlay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    setContentView(R.layout.activity_main);

    tvBest = findViewById(R.id.tvBest);
    btnPlay = findViewById(R.id.btnPlay);

    int best = PreferenceUtil.getBestScore(this);

    tvBest.setText("Best Score : " + best);

    btnPlay.setOnClickListener(
        v -> {
          startActivity(new Intent(MainActivity.this, GameActivity.class));
        });
  }

  @Override
  protected void onResume() {
    super.onResume();

    int best = PreferenceUtil.getBestScore(this);
    tvBest.setText("Best Score : " + best);
  }
}
