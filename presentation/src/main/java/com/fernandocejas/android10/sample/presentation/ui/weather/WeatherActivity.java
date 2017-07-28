package com.fernandocejas.android10.sample.presentation.ui.weather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.internal.di.HasComponent;
 import com.fernandocejas.android10.sample.presentation.internal.di.components.UserComponent;
import com.fernandocejas.android10.sample.presentation.view.activity.BaseActivity;

/**
 * Created by Ruby on 7/28/2017.
 */

public class WeatherActivity extends BaseActivity implements HasComponent<UserComponent> {

  private static final String INTENT_EXTRA_PARAM_CITY_ID = "org.android10.INTENT_PARAM_CITY_ID";
  private static final String INSTANCE_STATE_PARAM_CITY_ID = "org.android10.STATE_PARAM_CITY_ID";

  public static Intent getCallingIntent(Context context, int cityId) {
    Intent callingIntent = new Intent(context, WeatherActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_CITY_ID, cityId);
    return callingIntent;
  }

  private int cityId;
  private UserComponent userComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    if (outState != null) {
      outState.putInt(INSTANCE_STATE_PARAM_CITY_ID, this.cityId);
    }
    super.onSaveInstanceState(outState);
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      this.cityId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_CITY_ID, -1);
      addFragment(R.id.fragmentContainer, WeatherFragment.forCity(cityId));
    } else {
      this.cityId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_CITY_ID);
    }
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public UserComponent getComponent() {
    return userComponent;
  }
}
