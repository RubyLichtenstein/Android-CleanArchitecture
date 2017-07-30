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
import com.fernandocejas.android10.sample.presentation.internal.di.components.WeatherComponent;
import com.fernandocejas.android10.sample.presentation.ui.base.BaseFragment;
import com.fernandocejas.arrow.checks.Preconditions;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017
 */
public class WeatherFragment extends BaseFragment implements WeatherView {
  private static final String PARAM_CITY_ID = "param_city_id";

  @Inject WeatherPresenter weatherPresenter;

  @BindView(R.id.tv_city_name) TextView tvCityName;
  @BindView(R.id.tv_weather_description) TextView tvWeatherDescription;
  @BindView(R.id.imv_icon) ImageView imvIcon;
  @BindView(R.id.tv_current_temp) TextView tvCurrentTemp;
  @BindView(R.id.tv_today_temp_range) TextView tvTodayTempRange;
  @BindView(R.id.btn_celsius) Button btnCelsius;
  @BindView(R.id.btn_fahrenheit) Button btnFahrenheit;
  @BindView(R.id.progress_bar_weather) ProgressBar progressBar;

  public static WeatherFragment forCity(String cityId) {
    final WeatherFragment weatherFragment = new WeatherFragment();
    final Bundle arguments = new Bundle();
    arguments.putString(PARAM_CITY_ID, cityId);
    weatherFragment.setArguments(arguments);
    return weatherFragment;
  }

  public WeatherFragment() {
    setRetainInstance(true);
  }

  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    //setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setRetainInstance(true);
    this.getComponent(WeatherComponent.class).inject(this);
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

  @Override public void renderWeather(WeatherViewModel weather) {
    if (weather != null) {
      this.tvCityName.setText(weather.getCityName());
      this.tvWeatherDescription.setText(weather.getDescription());
      this.tvCurrentTemp.setText(weather.getCurrentTemp());
      this.tvTodayTempRange.setText(weather.getTodayTempRange());
      this.progressBar.setVisibility(View.GONE);
      ImageLoader.fromUrl(getActivity(), this.imvIcon, weather.getIconUrl());
    }
  }

  @Override public Observable<Object> celsiusBtnClick() {
    return RxView.clicks(btnCelsius);
  }

  @Override public Observable<Object> fahrenheitBtnClick() {
    return RxView.clicks(btnFahrenheit);
  }

  /**
   * Get current city id from fragments arguments.
   */
  private String currentCityId() {
    final Bundle arguments = getArguments();
    Preconditions.checkNotNull(arguments, getString(R.string.fragment_args_cannot_be_null));
    return arguments.getString(PARAM_CITY_ID);
  }

  private void loadWeather() {
    if (this.weatherPresenter != null) {
      this.weatherPresenter.initialize(currentCityId());
    }
  }

  @Override public void showLoading(boolean show) {
    this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return getActivity().getApplicationContext();
  }
}
