package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.Weather;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Ruby on 7/29/2017.
 */

//todo test
public class WeatherTempCalcImpl implements WeatherTempCalc {

  private final TempConverter tempConverter;
  private final Function<Weather, Weather> weatherTempCalc;

  public WeatherTempCalcImpl(final TempConverter tempConverter) {
    this.tempConverter = tempConverter;
    weatherTempCalc = new Function<Weather, Weather>() {
      @Override public Weather apply(@NonNull Weather weather) throws Exception {
        weather.setTempFahrenheit(tempConverter.toFahrenheit(weather.getTempCelsius()));
        weather.setTempFahrenheitMax(tempConverter.toFahrenheit(weather.getTempCelsiusMax()));
        weather.setTempFahrenheitMin(tempConverter.toFahrenheit(weather.getTempCelsiusMin()));
        return weather;
      }
    };
  }

  @Override public ObservableTransformer<Weather, Weather> applyTempCalc() {
    return new ObservableTransformer<Weather, Weather>() {
      @Override public ObservableSource<Weather> apply(@NonNull Observable<Weather> upstream) {
        return upstream.map(weatherTempCalc);
      }
    };
  }
}
