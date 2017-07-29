package com.fernandocejas.android10.sample.presentation.ui.citylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.internal.di.HasComponent;
import com.fernandocejas.android10.sample.presentation.internal.di.components.CityListComponent;
import com.fernandocejas.android10.sample.presentation.internal.di.components.DaggerCityListComponent;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.ui.base.BaseActivity;
import io.reactivex.functions.Consumer;

/**
 * Created by Ruby on 7/28/2017.
 */

public class CityListActivity extends BaseActivity implements HasComponent<CityListComponent> {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, CityListActivity.class);
  }

  private CityListComponent cityListComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout);

    this.initializeInjector();

    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new CityListFragment());
    }
  }

  private void initializeInjector() {
    this.cityListComponent = DaggerCityListComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public CityListComponent getComponent() {
    return cityListComponent;
  }

  public void onCityClicked(CityModel cityModel) {
    this.navigator.navigateToWeather(this, cityModel.getId());
  }

  private Consumer<CityModel> onCityClickObs = new Consumer<CityModel>() {
    @Override public void accept(CityModel cityModel) throws Exception {
      onCityClicked(cityModel);
    }
  };

  public Consumer<CityModel> getOnCityClickObserver() {
    return onCityClickObs;
  }
}
