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
}
