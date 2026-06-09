package com.ltd14.cellgate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
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
public class GameTouchTest {

  @Rule
  public ActivityScenarioRule<MainActivity> activityRule =
      new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void testSwipeDuringGameplay() {
    onView(withId(R.id.btnPlay)).perform(click());

    onView(withId(R.id.game_container)).perform(swipeLeft());
    onView(withId(R.id.game_container)).perform(swipeRight());

    onView(withId(R.id.game_container)).check(matches(isDisplayed()));
  }

  @Test
  public void testBackNavigationLogic() {
    onView(withId(R.id.btnPlay)).perform(click());

    pressBack();

    onView(withId(R.id.pauseMenu)).check(matches(isDisplayed()));

    pressBack();
    onView(withId(R.id.btnPlay)).check(matches(isDisplayed()));
  }
}
