package com.fernandocejas.android10.sample.test.ui.weather;

import android.app.Fragment;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Created by Ruby on 7/29/2017
 */
@RunWith(AndroidJUnit4.class) @LargeTest public class WeatherActivityTest {

  @Rule public ActivityTestRule<WeatherActivity> mActivityRule =
      new ActivityTestRule<>(WeatherActivity.class);

  @Test public void testContainsWeatherFragment() {
    Fragment weatherFragment =
        mActivityRule.getActivity().getFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(weatherFragment, is(notNullValue()));
  }

  @Test public void testContainsProperTitle() {
    String actualTitle = this.mActivityRule.getActivity().getTitle().toString().trim();

    assertThat(actualTitle,
        is(mActivityRule.getActivity().getString(R.string.activity_title_weather)));
  }

  @Test public void testViewsDisplayed() {
    onView(withId(R.id.tv_city_name)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_weather_description)).check(matches(isDisplayed()));
    onView(withId(R.id.imv_icon)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_current_temp)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_today_temp_range)).check(matches(isDisplayed()));

    onView(withId(R.id.btn_celsius)).check(matches(isDisplayed()));
    onView(withId(R.id.btn_celsius)).check(matches(isDisplayed()));
  }
}
