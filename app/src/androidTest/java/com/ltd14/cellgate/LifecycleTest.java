package com.ltd14.cellgate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import com.ltd14.cellgate.activities.GameActivity;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LifecycleTest {

  @Test
  public void testGamePausesWhenGoingToBackground() {
    try (ActivityScenario<GameActivity> scenario = ActivityScenario.launch(GameActivity.class)) {
      onView(withId(R.id.game_container)).check(matches(isDisplayed()));

      scenario.moveToState(androidx.lifecycle.Lifecycle.State.CREATED);

      scenario.moveToState(androidx.lifecycle.Lifecycle.State.RESUMED);

      onView(withId(R.id.pauseMenu)).check(matches(isDisplayed()));
    }
  }
}
