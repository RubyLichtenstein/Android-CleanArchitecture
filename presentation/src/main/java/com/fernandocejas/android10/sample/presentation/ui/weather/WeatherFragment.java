package com.fernandocejas.android10.sample.presentation.ui.weather;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.internal.di.components.UserComponent;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import com.fernandocejas.android10.sample.presentation.view.fragment.BaseFragment;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

public class WeatherFragment extends BaseFragment implements WeatherView {
  private static final String PARAM_CITY_ID = "param_city_id";

  @Inject WeatherPresenter weatherPresenter;

  @BindView(R.id.tv_city_name) TextView tvCityName;
  @BindView(R.id.tv_description) TextView tvDescription;
  @BindView(R.id.imv_icon) ImageView imvIcon;
  @BindView(R.id.tv_temp) TextView tvTemp;
  @BindView(R.id.tv_temp_min_max) TextView tvTempMinMax;
  @BindView(R.id.btn_celsius) Button btnCelsius;
  @BindView(R.id.btn_fahrenheit) Button btnFahrenheit;
  @BindView(R.id.progress_bar) ProgressBar progressBar;

  public static WeatherFragment forCity(int cityId) {
    final WeatherFragment weatherFragment = new WeatherFragment();
    final Bundle arguments = new Bundle();
    arguments.putInt(PARAM_CITY_ID, cityId);
    weatherFragment.setArguments(arguments);
    return weatherFragment;
  }

  public WeatherFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //todo
    this.getComponent(UserComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.weather_fragment, container, false);
    ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.weatherPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadWeather();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.weatherPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.weatherPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.weatherPresenter.destroy();
  }

  //todo use view model ???
  @Override public void renderWeather(WeatherModel weather) {
    if (weather != null) {
      this.tvCityName.setText(weather.getCityName());
      this.tvDescription.setText(weather.getDescription());
      // TODO: 7/28/2017
    }
  }

  /**
   * Get current city id from fragments arguments.
   */
  private int currentCityId() {
    final Bundle arguments = getArguments();
    Preconditions.checkNotNull(arguments, getString(R.string.fragment_args_cannot_be_null));
    return arguments.getInt(PARAM_CITY_ID);
  }

  /**
   * Load user details.
   */
  private void loadWeather() {
    if (this.weatherPresenter != null) {
      this.weatherPresenter.initialize(currentCityId());
    }
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  @Override public void showRetry() {

  }

  @Override public void hideRetry() {

  }

  @Override public void showError(String message) {

  }

  @Override public Context context() {
    return null;
  }
}
