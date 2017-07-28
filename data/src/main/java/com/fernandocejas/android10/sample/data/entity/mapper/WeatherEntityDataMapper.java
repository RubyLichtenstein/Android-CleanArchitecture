package com.fernandocejas.android10.sample.data.entity.mapper;

import android.support.annotation.Nullable;
import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import com.fernandocejas.android10.sample.domain.Weather;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class WeatherEntityDataMapper {

  @Inject public WeatherEntityDataMapper() {
  }

  @Nullable public Weather transform(WeatherEntity weatherEntity) {
    Weather weather = null;
    if (weatherEntity != null) {
      weather = new Weather();
      weather.setName(weatherEntity.getName());
      weather.setTempCelsius(weatherEntity.getMain().getTemp());
      weather.setTempCelsiusHigh(weatherEntity.getMain().getTempMax());
      weather.setTempCelsiusLow(weatherEntity.getMain().getTempMin());

      //todo find solution for long ref
      List<com.fernandocejas.android10.sample.data.entity.weather.Weather> weathers =
          weatherEntity.getWeather();

      if (weathers != null && weathers.size() > 0) {
        com.fernandocejas.android10.sample.data.entity.weather.Weather firstWeather =
            weathers.get(0);
        weather.setMain(firstWeather.getMain());
        weather.setDescription(firstWeather.getDescription());
        weather.setIcon(firstWeather.getIcon());
      }
    }
    return weather;
  }
}
