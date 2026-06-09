package com.ltd14.cellgate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import com.ltd14.cellgate.activities.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GameFlowTest {

  @Rule
  public ActivityScenarioRule<MainActivity> activityRule =
      new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void testCompleteUserJourney() {
    // 1. Check Main Screen
    onView(withText("CELL GATE")).check(matches(isDisplayed()));
    onView(withId(R.id.btnPlay)).perform(click());

    // 2. We are in Game
    onView(withId(R.id.game_container)).check(matches(isDisplayed()));

    // 3. Pause the game
    onView(withId(R.id.btnPause)).perform(click());
    onView(withId(R.id.pauseMenu)).check(matches(isDisplayed()));

    // 4. Resume the game
    onView(withId(R.id.btnResume)).perform(click());
    onView(withId(R.id.btnPause)).check(matches(isDisplayed()));

    // 5. Open Pause Menu again and go Home
    onView(withId(R.id.btnPause)).perform(click());
    onView(withId(R.id.btnHome)).perform(click());

    // 6. Verify we are back to Main Activity
    onView(withId(R.id.btnPlay)).check(matches(isDisplayed()));
  }
}
