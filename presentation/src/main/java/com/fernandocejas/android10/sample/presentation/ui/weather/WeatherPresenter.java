package com.fernandocejas.android10.sample.presentation.ui.weather;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.domain.interactor.GetWeather;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.mapper.WeatherModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

@PerActivity
public class WeatherPresenter implements Presenter {
  private WeatherView weatherView;

  private final GetWeather getWeatherUseCase;
  private final WeatherModelDataMapper weatherModelDataMapper;

  @Inject
  public WeatherPresenter(WeatherModelDataMapper weatherModelDataMapper,
      GetWeather getWeatherUseCase) {
    this.weatherModelDataMapper = weatherModelDataMapper;
    this.getWeatherUseCase = getWeatherUseCase;
  }

  public void setView(@NonNull WeatherView view) {
    this.weatherView = view;
  }

  /**
   * Initializes the presenter by showing/hiding proper views
   * and retrieving user details.
   */
  public void initialize(String cityId) {
    this.hideViewRetry();
    this.showViewLoading();
    this.getWeather(cityId);
  }

  private void showViewLoading() {
    //todo
  }

  private void hideViewRetry() {
    //todo
  }

  private void getWeather(String cityId) {
    this.getWeatherUseCase.execute(new WeatherObserver(), GetWeather.Params.forCity(cityId));
  }
  
  @Override public void resume() {

  }

  @Override public void pause() {

  }

  @Override public void destroy() {
    this.getWeatherUseCase.dispose();
    this.weatherView = null;
  }

  private void showViewRetry() {
    //todo
  }

  private void hideViewLoading() {
    //todo
  }

  private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
    //todo
  }

  private void showWeatherInView(Weather weather) {
    final WeatherModel weatherModel = this.weatherModelDataMapper.transform(weather);
    this.weatherView.renderWeather(weatherModel);
  }

  private final class WeatherObserver extends DefaultObserver<Weather> {

    @Override public void onComplete() {
      WeatherPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      WeatherPresenter.this.hideViewLoading();
      WeatherPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      WeatherPresenter.this.showViewRetry();
    }

    @Override public void onNext(Weather weather) {
      WeatherPresenter.this.showWeatherInView(weather);
    }
  }
}
