package com.ltd14.cellgate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import com.ltd14.cellgate.activities.GameActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GameActivityStateTest {

  @Rule
  public ActivityScenarioRule<GameActivity> activityRule =
      new ActivityScenarioRule<>(GameActivity.class);

  @Test
  public void testPauseResumeButtonVisibility() {
    onView(withId(R.id.pauseMenu)).check(matches(not(isDisplayed())));

    onView(withId(R.id.btnPause)).perform(click());

    onView(withId(R.id.pauseMenu)).check(matches(isDisplayed()));
    onView(withId(R.id.btnPause)).check(matches(not(isDisplayed())));

    onView(withId(R.id.btnResume)).perform(click());

    onView(withId(R.id.pauseMenu)).check(matches(not(isDisplayed())));
    onView(withId(R.id.btnPause)).check(matches(isDisplayed()));
  }
}
