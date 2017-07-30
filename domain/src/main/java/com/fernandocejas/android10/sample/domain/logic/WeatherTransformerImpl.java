package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.WeatherIn;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/29/2017.
 */

@Singleton public class WeatherTransformerImpl implements WeatherTransformer {

  private final TempConverter tempConverter;
  private final Function<WeatherIn, Weather> weatherTransformFunc;

  @Inject public WeatherTransformerImpl(@NonNull final TempConverter tempConverter) {
    this.tempConverter = tempConverter;
    weatherTransformFunc = new Function<WeatherIn, Weather>() {
      @Override public Weather apply(@NonNull WeatherIn weatherIn) throws Exception {
        Weather weather = new Weather();
        weather.setName(weatherIn.getName());
        weather.setMain(weatherIn.getMain());
        weather.setDescription(weatherIn.getDescription());
        weather.setIcon(weatherIn.getIcon());
        weather.setTempCelsius(weatherIn.getTempCelsius());
        weather.setTempCelsiusHigh(weatherIn.getTempCelsiusHigh());
        weather.setTempCelsiusLow(weatherIn.getTempCelsiusLow());
        weather.setTempFahrenheit(tempConverter.toFahrenheit(weather.getTempCelsius()));
        weather.setTempFahrenheitHigh(tempConverter.toFahrenheit(weather.getTempCelsiusHigh()));
        weather.setTempFahrenheitLow(tempConverter.toFahrenheit(weather.getTempCelsiusLow()));
        return weather;
      }
    };
  }

  @Override public ObservableTransformer<WeatherIn, Weather> apply() {
    return new ObservableTransformer<WeatherIn, Weather>() {
      @Override public ObservableSource<Weather> apply(@NonNull Observable<WeatherIn> upstream) {
        return upstream.map(weatherTransformFunc);
      }
    };
  }
}
