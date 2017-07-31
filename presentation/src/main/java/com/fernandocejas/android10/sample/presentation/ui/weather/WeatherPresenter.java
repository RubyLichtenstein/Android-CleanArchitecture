package com.fernandocejas.android10.sample.presentation.ui.weather;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.domain.interactor.GetWeather;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.mapper.WeatherModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

@PerActivity public class WeatherPresenter implements WeatherMvpContract.Presenter {
  private String cityId;

  //the initial state is to show temp in celsius
  private boolean celsius = true;

  //remember error state for screen rotation
  private boolean errorState;

  private WeatherMvpContract.View weatherView;
  private WeatherModel weatherModel;
  private CompositeDisposable compositeDisposable;
  private final GetWeather getWeatherUseCase;
  private final WeatherModelDataMapper weatherModelDataMapper;

  @Inject public WeatherPresenter(WeatherModelDataMapper weatherModelDataMapper,
      GetWeather getWeatherUseCase) {
    this.weatherModelDataMapper = weatherModelDataMapper;
    this.getWeatherUseCase = getWeatherUseCase;
    this.compositeDisposable = new CompositeDisposable();
  }

  public void setView(@NonNull WeatherMvpContract.View view) {
    this.weatherView = view;
    this.updateView();
  }

  private void updateView() {
    if (weatherModel != null && !errorState) {
      showWeatherInView(celsius, weatherModel);
      bindViewIntents();
    } else {
      //since i am not showing the user the exact reason
      weatherView.showError(null);
    }
  }

  /**
   * Initializes the presenter by showing/hiding proper views
   * and retrieving user details.
   */
  public void initialize(String cityId) {
    this.cityId = cityId;
    this.showViewLoading();
    this.getWeather(cityId);
  }

  private void showViewLoading() {
    this.weatherView.showLoading(true);
  }

  private void getWeather(String cityId) {
    this.getWeatherUseCase.execute(new WeatherObserver(), GetWeather.Params.forCity(cityId));
  }

  @Override public void resume() {
    bindViewIntents();
  }

  @Override public void pause() {
    if (this.compositeDisposable != null) {
      this.compositeDisposable.clear();
    }
  }

  @Override public void destroy() {
    this.compositeDisposable.dispose();
    this.getWeatherUseCase.dispose();
    this.weatherView = null;
  }

  private void hideViewLoading() {
    this.weatherView.showLoading(false);
  }

  private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
    this.weatherView.showError(null);
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

  @Override public void onCelsiusClick() {
    if (this.weatherModel != null) {
      showWeatherInView(this.celsius = true, this.weatherModel);
    }
  }

  @Override public void onFahrenheitClick() {
    showWeatherInView(this.celsius = false, this.weatherModel);
  }

  @Override public void onRefresh() {
    this.getWeather(cityId);
  }

  @Override public void bindViewIntents() {
    Disposable fahrenheitClickDisposable =
        this.weatherView.fahrenheitClick().subscribe(o -> onFahrenheitClick());

    Disposable celsiusClickDisposable =
        this.weatherView.celsiusClick().subscribe(o -> onCelsiusClick());

    Disposable refreshDisposabl = this.weatherView.refresh().subscribe(o -> onRefresh());

    compositeDisposable.add(refreshDisposabl);
    compositeDisposable.add(celsiusClickDisposable);
    compositeDisposable.add(fahrenheitClickDisposable);
  }

  private final class WeatherObserver extends DefaultObserver<Weather> {
    @Override public void onComplete() {
      WeatherPresenter.this.hideViewLoading();
      weatherView.setRefreshing(false);
    }

    @Override public void onError(Throwable e) {
      errorState = true;
      weatherView.setRefreshing(false);
      WeatherPresenter.this.hideViewLoading();
      WeatherPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
    }

    @Override public void onNext(Weather weather) {
      errorState = false;
      setWeatherModel(weather);
      updateView();
    }
  }
}
