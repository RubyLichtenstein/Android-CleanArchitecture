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
import io.reactivex.functions.Consumer;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

@PerActivity public class WeatherPresenter implements WeatherMvpContract.Presenter {
  private WeatherMvpContract.View weatherView;
  private WeatherModel weatherModel;
  private boolean celsius = true;
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
    if (weatherModel != null) {
      showWeatherInView(celsius, weatherModel);
      bindViewIntents();
    }
  }

  /**
   * Initializes the presenter by showing/hiding proper views
   * and retrieving user details.
   */
  public void initialize(String cityId) {
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

  @Override public void onCelsiusClick() {
    if (this.weatherModel != null) {
      showWeatherInView(this.celsius = true, this.weatherModel);
    }
  }

  @Override public void onFahrenheitClick() {
    showWeatherInView(this.celsius = false, this.weatherModel);
  }

  @Override public void bindViewIntents() {
    Disposable fahrenheitClickDisposable =
        this.weatherView.fahrenheitClick().subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            onFahrenheitClick();
          }
        });

    Disposable celsiusClickDisposable =
        this.weatherView.celsiusClick().subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {
            onCelsiusClick();
          }
        });
    compositeDisposable.add(celsiusClickDisposable);
    compositeDisposable.add(fahrenheitClickDisposable);
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
      updateView();
    }
  }
}
