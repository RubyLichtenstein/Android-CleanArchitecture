package com.fernandocejas.android10.sample.presentation.mapper;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherViewModel;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */
@PerActivity public class WeatherModelDataMapper {

  public static final String DEGREES = "°";

  @Inject public WeatherModelDataMapper() {
  }

  public WeatherModel transform(Weather weather) {
    if (weather == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
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
  public WeatherViewModel transform(boolean celsius, final WeatherModel wm) {
    if (wm == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    WeatherViewModel wvm = new WeatherViewModel();
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
    return new StringBuilder().append("Currently ")
        .append(String.valueOf(temp))
        .append(DEGREES)
        .toString();
  }

  private String prepareTempRange(float low, float high) {
    return new StringBuilder().append("Today ")
        .append(String.valueOf(low))
        .append(DEGREES)
        .append("-")
        .append(String.valueOf(high))
        .append(DEGREES)
        .toString();
  }
}
