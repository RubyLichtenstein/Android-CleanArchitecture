package com.fernandocejas.android10.sample.data.entity.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import com.fernandocejas.android10.sample.data.entity.weather.WeatherEntityInernal;
import com.fernandocejas.android10.sample.domain.WeatherRaw;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class WeatherEntityDataMapper {

  @Inject public WeatherEntityDataMapper() {
  }

  @Nullable public WeatherRaw transform(@NonNull WeatherEntity weatherEntity) {
    WeatherRaw weather = new WeatherRaw();
    weather.setName(weatherEntity.getName());
    weather.setTempCelsius(weatherEntity.getMain().getTemp());
    weather.setTempCelsiusHigh(weatherEntity.getMain().getTempMax());
    weather.setTempCelsiusLow(weatherEntity.getMain().getTempMin());

    List<WeatherEntityInernal> weathers = weatherEntity.getWeather();

    if (weathers != null && weathers.size() > 0) {
      WeatherEntityInernal firstWeather = weathers.get(0);
      weather.setMain(firstWeather.getMain());
      weather.setDescription(firstWeather.getDescription());
      weather.setIcon(firstWeather.getIcon());
    }

    return weather;
  }
}
