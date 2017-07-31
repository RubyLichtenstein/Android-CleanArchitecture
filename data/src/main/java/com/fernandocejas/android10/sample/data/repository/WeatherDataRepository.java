package com.fernandocejas.android10.sample.data.repository;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.data.entity.mapper.WeatherEntityDataMapper;
import com.fernandocejas.android10.sample.data.net.WeatherRestApi;
import com.fernandocejas.android10.sample.domain.WeatherRaw;
import com.fernandocejas.android10.sample.domain.repository.WeatherRepository;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class WeatherDataRepository implements WeatherRepository {

  private final WeatherRestApi weatherRestApi;
  private final WeatherEntityDataMapper weatherEntityDataMapper;

  @Inject public WeatherDataRepository(@NonNull WeatherRestApi weatherRestApi,
      @NonNull WeatherEntityDataMapper weatherEntityDataMapper) {
    this.weatherRestApi = weatherRestApi;
    this.weatherEntityDataMapper = weatherEntityDataMapper;
  }

  @Override public Observable<WeatherRaw> weather(String cityId) {
    return weatherRestApi.weatherEntityByCityId(cityId).map(weatherEntityDataMapper::transform);
  }
}
