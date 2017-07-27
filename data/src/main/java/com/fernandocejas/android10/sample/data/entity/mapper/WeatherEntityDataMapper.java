package com.fernandocejas.android10.sample.data.entity.mapper;

import android.support.annotation.Nullable;
import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import com.fernandocejas.android10.sample.domain.Weather;
import java.util.List;

/**
 * Created by Ruby on 7/26/2017.
 */

public class WeatherEntityDataMapper {

  @Nullable public Weather transform(WeatherEntity weatherEntity) {
    Weather weather = null;
    if (weatherEntity != null) {
      weather = new Weather();
      weather.setName(weatherEntity.getName());
      weather.setTemp(weatherEntity.getMain().getTemp());
      weather.setTempMax(weatherEntity.getMain().getTempMax());
      weather.setTempMin(weatherEntity.getMain().getTempMin());

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
