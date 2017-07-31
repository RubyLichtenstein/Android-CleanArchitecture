package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.WeatherRaw;
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
  private final Function<WeatherRaw, Weather> weatherTransformFunc;

  @Inject public WeatherTransformerImpl(@NonNull final TempConverter tempConverter) {
    this.tempConverter = tempConverter;
    weatherTransformFunc = new Function<WeatherRaw, Weather>() {
      @Override public Weather apply(@NonNull WeatherRaw weatherRaw) throws Exception {
        Weather weather = new Weather();
        weather.setName(weatherRaw.getName());
        weather.setMain(weatherRaw.getMain());
        weather.setDescription(weatherRaw.getDescription());
        weather.setIcon(weatherRaw.getIcon());
        weather.setTempCelsius(weatherRaw.getTempCelsius());
        weather.setTempCelsiusHigh(weatherRaw.getTempCelsiusHigh());
        weather.setTempCelsiusLow(weatherRaw.getTempCelsiusLow());
        weather.setTempFahrenheit(tempConverter.toFahrenheit(weather.getTempCelsius()));
        weather.setTempFahrenheitHigh(tempConverter.toFahrenheit(weather.getTempCelsiusHigh()));
        weather.setTempFahrenheitLow(tempConverter.toFahrenheit(weather.getTempCelsiusLow()));
        return weather;
      }
    };
  }

  @Override public ObservableTransformer<WeatherRaw, Weather> apply() {
    return new ObservableTransformer<WeatherRaw, Weather>() {
      @Override public ObservableSource<Weather> apply(@NonNull Observable<WeatherRaw> upstream) {
        return upstream.map(weatherTransformFunc);
      }
    };
  }
}
