package com.fernandocejas.android10.sample.test.ui.citylist;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ruby on 7/28/2017.
 */

public class CityListActivityTest extends ActivityInstrumentationTestCase2<CityListActivity> {


  private CityListActivity cityListActivity;

  public CityListActivityTest() {
    super(CityListActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    cityListActivity = getActivity();
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsUserListFragment() {
    Fragment cityListFragment =
        cityListActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(cityListFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.cityListActivity.getTitle().toString().trim();

    assertThat(actualTitle, is(getActivity().getString(R.string.activity_title_city_list)));
  }

  private Intent createTargetIntent() {
    Intent intentLaunchActivity =
        CityListActivity.getCallingIntent(getInstrumentation().getTargetContext());

    return intentLaunchActivity;
  }
}
