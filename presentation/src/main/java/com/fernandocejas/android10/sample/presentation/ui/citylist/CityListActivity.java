package com.fernandocejas.android10.sample.presentation.ui.citylist;

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

public class CityListActivity extends BaseActivity implements HasComponent<UserComponent> {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, CityListActivity.class);
  }

  private UserComponent userComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeInjector();
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new CityListFragment());
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

  //public void onCityClicked(CityModel cityModel) {
  //  this.navigator.navigateToWeather(this, cityModel.getId());
  //}
}
