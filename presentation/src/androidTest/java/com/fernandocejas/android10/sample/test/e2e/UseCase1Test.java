package com.fernandocejas.android10.sample.test.e2e;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListActivity;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherActivity;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created by Ruby on 7/29/2017
 */

@RunWith(AndroidJUnit4.class) @LargeTest public class UseCase1Test {
  private static final String CURRENTLY = "Currently ";
  private static final String TODAY = "Today ";

  @Rule public IntentsTestRule<CityListActivity> mIntentActivityRule =
      new IntentsTestRule<>(CityListActivity.class);

  @Test public void appUseCaseFlow1() {
    int rvItem = 0;
    //click on first rv item
    onView(withId(R.id.rv_cities)).perform(RecyclerViewActions.actionOnItemAtPosition(rvItem, click()));

    //verify WeatherActivity opened
    intended(hasComponent(WeatherActivity.class.getName()));

    //verify all views are displayed
    onView(withId(R.id.tv_city_name)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_weather_description)).check(matches(isDisplayed()));
    onView(withId(R.id.imv_icon)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_current_temp)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_today_temp_range)).check(matches(isDisplayed()));

    onView(withId(R.id.btn_celsius)).check(matches(isDisplayed()));
    onView(withId(R.id.btn_fahrenheit)).check(matches(isDisplayed()));

    //verify specific text is displayed
    onView(withId(R.id.tv_current_temp)).check(matches(withText(startsWith(CURRENTLY))));
    onView(withId(R.id.tv_today_temp_range)).check(matches(withText(startsWith(TODAY))));

    //test that tv_current_temp text change after btn_celsius click
    onView(withId(R.id.btn_celsius)).perform(click());
    String currentTempBeforeClick = getText(withId(R.id.tv_current_temp));
    String tempRangeBeforeClick = getText(withId(R.id.tv_today_temp_range));
    onView(withId(R.id.btn_fahrenheit)).perform(click());
    String currentTempAfterClick = getText(withId(R.id.tv_current_temp));
    String tempRangeAfterClick = getText(withId(R.id.tv_today_temp_range));
    assertThat(currentTempBeforeClick, is(not(currentTempAfterClick)));
    assertThat(tempRangeBeforeClick, is(not(tempRangeAfterClick)));
  }

  public String getText(final Matcher<View> matcher) {
    final String[] stringHolder = { null };
    onView(matcher).perform(new ViewAction() {
      @Override public Matcher<View> getConstraints() {
        return isAssignableFrom(TextView.class);
      }

      @Override public String getDescription() {
        return "getting text from a TextView";
      }

      @Override public void perform(UiController uiController, View view) {
        TextView tv = (TextView) view; //Save, because of check in getConstraints()
        stringHolder[0] = tv.getText().toString();
      }
    });
    return stringHolder[0];
  }
}
