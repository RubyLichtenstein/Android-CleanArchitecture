package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.WeatherIn;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Ruby on 7/29/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherTransformerTest {
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

  @InjectMocks WeatherTransformerImpl weatherTransformer;

  @Mock TempConverter mockTempConverter;

  @Before public void setUp() {
  }

  @Test public void testWeatherTransformApply() {
    when(mockTempConverter.toFahrenheit(TEMP_CELSIUS)).thenReturn(TEMP_FAHRENHEIT);
    when(mockTempConverter.toFahrenheit(TEMP_CELSIUS_HIGH)).thenReturn(TEMP_FAHRENHEIT_HIGH);
    when(mockTempConverter.toFahrenheit(TEMP_CELSIUS_LOW)).thenReturn(TEMP_FAHRENHEIT_LOW);

    WeatherIn weatherIn = createWeatherIn();
    Observable<WeatherIn> weatherInObservable = Observable.just(weatherIn);

    TestObserver<Weather> testObserver = new TestObserver<>();
    weatherInObservable.compose(weatherTransformer.apply()).subscribe(testObserver);

    testObserver.assertComplete();
    testObserver.assertNoErrors();

    Weather weather = testObserver.values().get(0);

    assertThat(weather.getMain()).isEqualTo(MAIN);
    assertThat(weather.getDescription()).isEqualTo(DESCRIPTION);
    assertThat(weather.getIcon()).isEqualTo(ICON);
    assertThat(weather.getName()).isEqualTo(NAME);
    assertThat(weather.getTempCelsius()).isEqualTo(TEMP_CELSIUS);
    assertThat(weather.getTempCelsiusHigh()).isEqualTo(TEMP_CELSIUS_HIGH);
    assertThat(weather.getTempCelsiusLow()).isEqualTo(TEMP_CELSIUS_LOW);
    assertThat(weather.getTempFahrenheit()).isEqualTo(TEMP_FAHRENHEIT);
    assertThat(weather.getTempFahrenheitHigh()).isEqualTo(TEMP_FAHRENHEIT_HIGH);
    assertThat(weather.getTempFahrenheitLow()).isEqualTo(TEMP_FAHRENHEIT_LOW);
  }

  public WeatherIn createWeatherIn() {
    WeatherIn weatherIn = new WeatherIn();
    weatherIn.setDescription(DESCRIPTION);
    weatherIn.setIcon(ICON);
    weatherIn.setMain(MAIN);
    weatherIn.setName(NAME);
    weatherIn.setTempCelsius(TEMP_CELSIUS);
    weatherIn.setTempCelsiusHigh(TEMP_CELSIUS_HIGH);
    weatherIn.setTempCelsiusLow(TEMP_CELSIUS_LOW);
    return weatherIn;
  }
}
