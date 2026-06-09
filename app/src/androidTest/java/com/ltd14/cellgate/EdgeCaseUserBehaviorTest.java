package com.ltd14.cellgate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import com.ltd14.cellgate.activities.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EdgeCaseUserBehaviorTest {

  @Rule
  public ActivityScenarioRule<MainActivity> activityRule =
      new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void testRapidPauseResumeSpam() {
    onView(withId(R.id.btnPlay)).perform(click());

    // Spam pause/resume buttons
    for (int i = 0; i < 5; i++) {
      onView(withId(R.id.btnPause)).perform(click());
      onView(withId(R.id.btnResume)).perform(click());
    }

    // Should still be in a stable state
    onView(withId(R.id.btnPause)).check(matches(isDisplayed()));
  }

  @Test
  public void testExtremeSwiping() {
    onView(withId(R.id.btnPlay)).perform(click());

    // Perform multiple rapid swipes
    for (int i = 0; i < 10; i++) {
      onView(withId(R.id.game_container)).perform(swipeLeft());
      onView(withId(R.id.game_container)).perform(swipeRight());
    }

    // Ensure app remains stable
    onView(withId(R.id.game_container)).check(matches(isDisplayed()));
  }
}
