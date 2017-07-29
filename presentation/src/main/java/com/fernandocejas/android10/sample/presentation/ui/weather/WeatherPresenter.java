package com.fernandocejas.android10.sample.presentation.ui.weather;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.domain.interactor.GetWeather;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.mapper.WeatherModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import com.fernandocejas.android10.sample.presentation.ui.base.Presenter;
import io.reactivex.functions.Consumer;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

@PerActivity public class WeatherPresenter implements Presenter {
  private WeatherView weatherView;
  private WeatherModel weatherModel;
  private boolean celsius = true;

  private final GetWeather getWeatherUseCase;
  private final WeatherModelDataMapper weatherModelDataMapper;

  @Inject public WeatherPresenter(WeatherModelDataMapper weatherModelDataMapper,
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
    this.showViewLoading();
    this.getWeather(cityId);
    this.initializeViewObservers();
  }

  private void initializeViewObservers() {
    this.weatherView.fahrenheitBtnClick().subscribe(new Consumer<Object>() {
      @Override public void accept(Object o) throws Exception {
        onFahrenheitClick();
      }
    });

    this.weatherView.celsiusBtnClick().subscribe(new Consumer<Object>() {
      @Override public void accept(Object o) throws Exception {
        onCelsiusClick();
      }
    });
  }

  private void showViewLoading() {
    this.weatherView.showLoading(true);
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

  private void hideViewLoading() {
    this.weatherView.showLoading(false);
  }

  private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
    this.weatherView.showError(defaultErrorBundle.getErrorMessage());
  }

  public void showWeatherInView(boolean celsius, WeatherModel weatherModel) {
    if (weatherModel != null) {
      this.weatherView.renderWeather(weatherModelDataMapper.transform(celsius, weatherModel));
    }
  }

  public void setWeatherModel(Weather weather) {
    if (weather != null) {
      this.weatherModel = this.weatherModelDataMapper.transform(weather);
    }
  }

  public void onCelsiusClick() {
    if (this.weatherModel != null) {
      showWeatherInView(this.celsius = true, this.weatherModel);
    }
  }

  private void onFahrenheitClick() {
    showWeatherInView(this.celsius = false, this.weatherModel);
  }

  private final class WeatherObserver extends DefaultObserver<Weather> {
    @Override public void onComplete() {
      WeatherPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      WeatherPresenter.this.hideViewLoading();
      WeatherPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
    }

    @Override public void onNext(Weather weather) {
      setWeatherModel(weather);
      showWeatherInView(celsius, weatherModel);
    }
  }
}
