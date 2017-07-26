package com.fernandocejas.android10.sample.data.repository;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.repository.WeatherRepository;
import io.reactivex.Observable;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton
public class WeatherDataRepository implements WeatherRepository {
  @Override public Observable<Weather> weather(int cityId) {
    return null;
  }
}
