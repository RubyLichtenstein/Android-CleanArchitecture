package com.fernandocejas.android10.sample.test.e2e;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListActivity;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created by Ruby on 7/29/2017
 */

@RunWith(AndroidJUnit4.class) @LargeTest public class AppFlowTest {
  public static final String CURRENTLY = "Currently ";
  public static final String TODAY = "Today ";

  @Rule public IntentsTestRule<CityListActivity> mIntentActivityRule =
      new IntentsTestRule<>(CityListActivity.class);

  @Test public void testFlow() {
    //click on first rv item
    onView(withId(R.id.rv_cities)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    //verify WeatherActivity opened
    intended(hasComponent(WeatherActivity.class.getName()));

    //verify all views are displayed
    onView(withId(R.id.tv_city_name)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_weather_description)).check(matches(isDisplayed()));
    onView(withId(R.id.imv_icon)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_current_temp)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_today_temp_range)).check(matches(isDisplayed()));

    onView(withId(R.id.btn_celsius)).check(matches(isDisplayed()));
    onView(withId(R.id.btn_celsius)).check(matches(isDisplayed()));

    //verify specific text is displayed
    onView(withId(R.id.tv_current_temp)).check(matches(withText(startsWith(CURRENTLY))));
    onView(withId(R.id.tv_today_temp_range)).check(matches(withText(startsWith(TODAY))));
  }
}
