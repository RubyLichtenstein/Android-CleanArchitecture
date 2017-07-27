package com.fernandocejas.android10.sample.data.repository;

import com.fernandocejas.android10.sample.data.entity.mapper.WeatherEntityDataMapper;
import com.fernandocejas.android10.sample.data.net.WeatherRestApi;
import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.repository.WeatherRepository;
import io.reactivex.Observable;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class WeatherDataRepository implements WeatherRepository {

  private final WeatherRestApi weatherRestApi;
  private final WeatherEntityDataMapper weatherEntityDataMapper;

  public WeatherDataRepository(WeatherRestApi weatherRestApi,
      WeatherEntityDataMapper weatherEntityDataMapper) {
    this.weatherRestApi = weatherRestApi;
    this.weatherEntityDataMapper = weatherEntityDataMapper;
  }

  @Override public Observable<Weather> weather(int cityId) {
    return weatherRestApi.WeatherEntityByCityId(cityId).map(weatherEntityDataMapper::transform);
  }
}
