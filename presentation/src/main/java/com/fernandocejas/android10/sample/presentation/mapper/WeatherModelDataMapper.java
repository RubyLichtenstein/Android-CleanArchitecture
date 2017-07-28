package com.fernandocejas.android10.sample.presentation.mapper;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
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
    weatherModel.setCurrentlyTempCelsius(prepareCurrentlyTemp(weather.getTempCelsius()));
    weatherModel.setCurrentlyTempFahrenheit(prepareCurrentlyTemp(weather.getTempCelsius()));
    weatherModel.setTodayTempRangeCelsius(
        prepareTempRange(weather.getTempCelsiusLow(), weather.getTempCelsiusHigh()));
    weatherModel.setTodayTempRangeFahrenheit(
        prepareTempRange(weather.getTempFahrenheitLow(), weather.getTempFahrenheitHigh()));
    return weatherModel;
  }

  private String prepareIconUrl(String icon) {
    return String.format("http://openweathermap.org/img/w/%s", icon);
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
