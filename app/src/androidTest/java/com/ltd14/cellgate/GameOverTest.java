package com.ltd14.cellgate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import android.content.Context;
import android.content.Intent;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import com.ltd14.cellgate.activities.GameOverActivity;
import com.ltd14.cellgate.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GameOverTest {

  @Test
  public void testGameOverUI() {
    Context context = ApplicationProvider.getApplicationContext();
    Intent intent = new Intent(context, GameOverActivity.class);
    intent.putExtra(Constants.EXTRA_SCORE, 100);

    try (var scenario = androidx.test.core.app.ActivityScenario.launch(intent)) {
      onView(withText("GAME OVER")).check(matches(isDisplayed()));

      onView(withId(R.id.tvScore)).check(matches(withText(containsString("100"))));

      onView(withId(R.id.btnRetry)).check(matches(isDisplayed()));
      onView(withId(R.id.btnHome)).check(matches(isDisplayed()));
    }
  }

  @Test
  public void testRetryButton() {
    Context context = ApplicationProvider.getApplicationContext();
    Intent intent = new Intent(context, GameOverActivity.class);

    try (var scenario = androidx.test.core.app.ActivityScenario.launch(intent)) {
      // Click Retry
      onView(withId(R.id.btnRetry)).perform(click());

      // Sau khi retry, GameActivity nên được hiển thị
      onView(withId(R.id.game_container)).check(matches(isDisplayed()));
    }
  }

  @Test
  public void testHomeButton() {
    Context context = ApplicationProvider.getApplicationContext();
    Intent intent = new Intent(context, GameOverActivity.class);

    try (var scenario = androidx.test.core.app.ActivityScenario.launch(intent)) {
      // Click Home
      onView(withId(R.id.btnHome)).perform(click());

      // Quay lại màn hình chính
      onView(withId(R.id.btnPlay)).check(matches(isDisplayed()));
    }
  }
}
