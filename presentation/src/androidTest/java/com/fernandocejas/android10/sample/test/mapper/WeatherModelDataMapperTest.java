package com.fernandocejas.android10.sample.test.mapper;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.presentation.mapper.WeatherModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherViewModel;
import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ruby on 7/28/2017.
 */

public class WeatherModelDataMapperTest extends TestCase {
  public static final String TODAY = "Today ";
  public static final String HYPHEN = "-";
  public static final String PNG = ".png";
  public static final String DEGREES = "°";
  public static final String CURRENTLY = "Currently ";
  public static final String DESCRIPTION = "description";
  public static final String ICON = "icon";
  public static final String MAIN = "main";
  public static final String NAME = "name";
  public static final float TEMP_CELSIUS = 10;
  public static final float TEMP_CELSIUS_HIGH = 20;
  public static final float TEMP_CELSIUS_LOW = 0;
  public static final float TEMP_FAHRENHEIT = 15;
  public static final float TEMP_FAHRENHEIT_HIGH = 25;
  public static final float TEMP_FAHRENHEIT_LOW = 5;

  public static final String ICON_URL = "http://openweathermap.org/img/w/" + ICON + PNG;

  public static final String PREPARED_CURRENTLY_TEMP_CELSIUS =
      CURRENTLY + String.valueOf(TEMP_CELSIUS) + DEGREES;

  public static final String PREPARED_CURRENTLY_TEMP_FAHRENHEIT =
      CURRENTLY + String.valueOf(TEMP_FAHRENHEIT) + DEGREES;

  public static final String PREPARED_TEMP_RANGE_CELSIUS =
      TODAY + String.valueOf(TEMP_CELSIUS_LOW) + DEGREES + HYPHEN + String.valueOf(
          TEMP_CELSIUS_HIGH) + DEGREES;

  public static final String PREPARED_TEMP_RANGE_FAHRENHEIT =
      TODAY + String.valueOf(TEMP_FAHRENHEIT_LOW) + DEGREES + HYPHEN + String.valueOf(
          TEMP_FAHRENHEIT_HIGH) + DEGREES;

  private WeatherModelDataMapper weatherModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    weatherModelDataMapper = new WeatherModelDataMapper();
  }

  public void testTransformWeather() {
    Weather weather = createWeather();
    WeatherModel weatherModel = weatherModelDataMapper.transform(weather);
    assertThat(weatherModel.getCityName(), is(NAME));
    assertThat(weatherModel.getDescription(), is(DESCRIPTION));
    assertThat(weatherModel.getIconUrl(), is(ICON_URL));
    assertThat(weatherModel.getCurrentTempCelsius(), is(PREPARED_CURRENTLY_TEMP_CELSIUS));
    assertThat(weatherModel.getCurrentTempFahrenheit(), is(PREPARED_CURRENTLY_TEMP_FAHRENHEIT));
    assertThat(weatherModel.getTodayTempRangeCelsius(), is(PREPARED_TEMP_RANGE_CELSIUS));
    assertThat(weatherModel.getTodayTempRangeFahrenheit(), is(PREPARED_TEMP_RANGE_FAHRENHEIT));
  }

  public void testTransformViewModelFahrenheit() {
    WeatherModel weatherModel = createWeatherModel();
    WeatherViewModel weatherViewModel = weatherModelDataMapper.transform(false, weatherModel);
    assertThat(weatherViewModel.getCityName(), is(NAME));
    assertThat(weatherViewModel.getDescription(), is(DESCRIPTION));
    assertThat(weatherViewModel.getIconUrl(), is(ICON_URL));
    assertThat(weatherViewModel.getCurrentTemp(), is(PREPARED_CURRENTLY_TEMP_FAHRENHEIT));
    assertThat(weatherViewModel.getTodayTempRange(), is(PREPARED_TEMP_RANGE_FAHRENHEIT));
  }

  public void testTransformViewModelCelsuies() {
    WeatherModel weatherModel = createWeatherModel();
    WeatherViewModel weatherViewModel = weatherModelDataMapper.transform(true, weatherModel);
    assertThat(weatherViewModel.getCityName(), is(NAME));
    assertThat(weatherViewModel.getDescription(), is(DESCRIPTION));
    assertThat(weatherViewModel.getIconUrl(), is(ICON_URL));
    assertThat(weatherViewModel.getCurrentTemp(), is(PREPARED_CURRENTLY_TEMP_CELSIUS));
    assertThat(weatherViewModel.getTodayTempRange(), is(PREPARED_TEMP_RANGE_CELSIUS));
  }

  private WeatherModel createWeatherModel() {
    WeatherModel weatherModel = new WeatherModel();
    weatherModel.setCityName(NAME);
    weatherModel.setDescription(DESCRIPTION);
    weatherModel.setIconUrl(ICON_URL);
    weatherModel.setCurrentTempCelsius(PREPARED_CURRENTLY_TEMP_CELSIUS);
    weatherModel.setCurrentTempFahrenheit(PREPARED_CURRENTLY_TEMP_FAHRENHEIT);
    weatherModel.setTodayTempRangeCelsius(PREPARED_TEMP_RANGE_CELSIUS);
    weatherModel.setTodayTempRangeFahrenheit(PREPARED_TEMP_RANGE_FAHRENHEIT);
    return weatherModel;
  }

  private Weather createWeather() {
    Weather weather = new Weather();
    weather.setDescription(DESCRIPTION);
    weather.setIcon(ICON);
    weather.setMain(MAIN);
    weather.setName(NAME);
    weather.setTempCelsius(TEMP_CELSIUS);
    weather.setTempCelsiusHigh(TEMP_CELSIUS_HIGH);
    weather.setTempCelsiusLow(TEMP_CELSIUS_LOW);
    weather.setTempFahrenheit(TEMP_FAHRENHEIT);
    weather.setTempFahrenheitHigh(TEMP_FAHRENHEIT_HIGH);
    weather.setTempFahrenheitLow(TEMP_FAHRENHEIT_LOW);
    return weather;
  }
}
