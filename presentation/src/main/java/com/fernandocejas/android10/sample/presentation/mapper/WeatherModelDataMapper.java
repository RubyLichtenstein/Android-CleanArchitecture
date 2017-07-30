package com.fernandocejas.android10.sample.presentation.mapper;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherViewModel;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */
@PerActivity public class WeatherModelDataMapper {

  private static final String DEGREES = "°";

  @Inject public WeatherModelDataMapper() {
  }

  public WeatherModel transform(@NonNull Weather weather) {
    final WeatherModel weatherModel = new WeatherModel();
    weatherModel.setCityName(weather.getName());
    weatherModel.setDescription(weather.getDescription());
    weatherModel.setIconUrl(prepareIconUrl(weather.getIcon()));
    weatherModel.setCurrentTempCelsius(prepareCurrentlyTemp(weather.getTempCelsius()));
    weatherModel.setCurrentTempFahrenheit(prepareCurrentlyTemp(weather.getTempFahrenheit()));
    weatherModel.setTodayTempRangeCelsius(
        prepareTempRange(weather.getTempCelsiusLow(), weather.getTempCelsiusHigh()));
    weatherModel.setTodayTempRangeFahrenheit(
        prepareTempRange(weather.getTempFahrenheitLow(), weather.getTempFahrenheitHigh()));
    return weatherModel;
  }

  //todo test
  public WeatherViewModel transform(boolean celsius, @NonNull final WeatherModel wm) {
    final WeatherViewModel wvm = new WeatherViewModel();
    wvm.setCityName(wm.getCityName());
    wvm.setDescription(wm.getDescription());
    wvm.setIconUrl(wm.getIconUrl());
    wvm.setCurrentTemp(celsius ? wm.getCurrentTempCelsius() : wm.getCurrentTempFahrenheit());
    wvm.setTodayTempRange(
        celsius ? wm.getTodayTempRangeCelsius() : wm.getTodayTempRangeFahrenheit());
    return wvm;
  }

  private String prepareIconUrl(String icon) {
    return String.format("http://openweathermap.org/img/w/%s%s", icon, ".png");
  }

  private String prepareCurrentlyTemp(float temp) {
    return "Currently " + String.valueOf(temp) + DEGREES;
  }

  private String prepareTempRange(float low, float high) {
    return "Today " + String.valueOf(low) + DEGREES + "-" + String.valueOf(high) + DEGREES;
  }
}
