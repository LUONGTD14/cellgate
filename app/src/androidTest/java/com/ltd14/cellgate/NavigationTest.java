package com.ltd14.cellgate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import com.ltd14.cellgate.activities.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationTest {

  @Rule
  public ActivityScenarioRule<MainActivity> activityRule =
      new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void testMainActivityUI() {
    onView(withText("CELL GATE")).check(matches(isDisplayed()));

    onView(withId(R.id.btnPlay)).check(matches(isDisplayed()));

    onView(withId(R.id.tvBest)).check(matches(withText(containsString("Best Score"))));
  }

  @Test
  public void testNavigateToGameAndPause() {
    onView(withId(R.id.btnPlay)).perform(click());

    onView(withId(R.id.game_container)).check(matches(isDisplayed()));

    onView(withId(R.id.btnPause)).check(matches(isDisplayed()));

    onView(withId(R.id.btnPause)).perform(click());

    onView(withId(R.id.pauseMenu)).check(matches(isDisplayed()));

    onView(withId(R.id.btnResume)).check(matches(isDisplayed()));

    onView(withId(R.id.btnResume)).perform(click());

    onView(withId(R.id.pauseMenu)).check(matches(not(isDisplayed())));
  }

  @Test
  public void testReturnToHomeFromGame() {
    onView(withId(R.id.btnPlay)).perform(click());

    onView(withId(R.id.btnPause)).perform(click());

    onView(withId(R.id.btnHome)).perform(click());

    onView(withId(R.id.btnPlay)).check(matches(isDisplayed()));
  }
}
