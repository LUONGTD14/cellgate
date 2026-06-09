package com.ltd14.cellgate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import android.content.Context;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import com.ltd14.cellgate.activities.MainActivity;
import com.ltd14.cellgate.util.PreferenceUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScoreIntegrationTest {

  @Before
  public void resetScores() {
    Context context = ApplicationProvider.getApplicationContext();
    PreferenceUtil.saveBestScore(context, 0);
  }

  @Test
  public void testScoreUpdatesOnMainActivity() {
    Context context = ApplicationProvider.getApplicationContext();

    PreferenceUtil.saveBestScore(context, 250);

    try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
      onView(withId(R.id.tvBest)).check(matches(withText(containsString("250"))));
    }
  }
}
