package com.fernandocejas.android10.sample.test.ui.citylist;

import android.app.Fragment;
import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ruby on 7/28/2017.
 */
@RunWith(AndroidJUnit4.class) @LargeTest public class CityListActivityTest {

  @Rule public ActivityTestRule<CityListActivity> mActivityRule =
      new ActivityTestRule<>(CityListActivity.class);

  @Test public void testContainsUserListFragment() {
    Fragment cityListFragment =
        mActivityRule.getActivity().getFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(cityListFragment, is(notNullValue()));
  }

  @Test public void testContainsProperTitle() {
    String actualTitle = this.mActivityRule.getActivity().getTitle().toString().trim();

    assertThat(actualTitle,
        is(mActivityRule.getActivity().getString(R.string.activity_title_city_list)));
  }

  private Intent createTargetIntent() {
    Intent intentLaunchActivity = CityListActivity.getCallingIntent(mActivityRule.getActivity());

    return intentLaunchActivity;
  }
}
