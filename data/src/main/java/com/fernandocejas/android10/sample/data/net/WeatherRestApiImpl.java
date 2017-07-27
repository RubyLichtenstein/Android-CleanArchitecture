package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import io.reactivex.Observable;

/**
 * Created by Ruby on 7/27/2017.
 */

public class WeatherRestApiImpl implements WeatherRestApi {
  private final WeatherRestApi weatherRestApi;

  public WeatherRestApiImpl(WeatherRestApi weatherRestApi) {
    this.weatherRestApi = weatherRestApi;
    //todo inject?
    //HttpClient<WeatherRestApi> httpClient = new HttpClient<>(baseUrl, WeatherRestApi.class);
  }

  @Override public Observable<WeatherEntity> WeatherEntityByCityId(int cityId) {
    return weatherRestApi.WeatherEntityByCityId(cityId);
  }
}
