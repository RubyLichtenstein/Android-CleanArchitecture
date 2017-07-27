package com.fernandocejas.android10.sample.presentation.ui.weather;

import com.fernandocejas.android10.sample.domain.interactor.GetWeather;
import com.fernandocejas.android10.sample.presentation.mapper.WeatherModelDataMapper;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;

/**
 * Created by Ruby on 7/28/2017.
 */

public class WeatherPresenter implements Presenter {
  private WeatherView weatherView;
  private final WeatherModelDataMapper weatherModelDataMapper;
  private final GetWeather getWeatherUseCase;

  public WeatherPresenter(WeatherModelDataMapper weatherModelDataMapper,
      GetWeather getWeatherUseCase) {
    this.weatherModelDataMapper = weatherModelDataMapper;
    this.getWeatherUseCase = getWeatherUseCase;
  }

  @Override public void resume() {

  }

  @Override public void pause() {

  }

  @Override public void destroy() {
    this.getWeatherUseCase.dispose();
    this.weatherView = null;
  }
}
