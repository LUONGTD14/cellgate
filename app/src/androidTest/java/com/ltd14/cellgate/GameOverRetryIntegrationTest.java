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
public class GameOverRetryIntegrationTest {

  @Test
  public void testRetryFlowResetsGame() {
    Context context = ApplicationProvider.getApplicationContext();

    Intent intent = new Intent(context, GameOverActivity.class);
    intent.putExtra(Constants.EXTRA_SCORE, 500);

    try (var scenario = androidx.test.core.app.ActivityScenario.launch(intent)) {
      onView(withId(R.id.tvScore)).check(matches(withText(containsString("500"))));

      onView(withId(R.id.btnRetry)).perform(click());

      onView(withId(R.id.game_container)).check(matches(isDisplayed()));
    }
  }
}
