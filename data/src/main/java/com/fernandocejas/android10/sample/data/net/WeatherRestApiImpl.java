package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import io.reactivex.Observable;

/**
 * Created by Ruby on 7/27/2017.
 */

public class WeatherRestApiImpl implements WeatherRestApi {
  private final String baseUrl;
  private WeatherRestApi weatherRestApi;

  public WeatherRestApiImpl(String baseUrl) {
    this.baseUrl = baseUrl;

    //todo inject?
    HttpClient<WeatherRestApi> httpClient =
        new HttpClient<>(baseUrl, WeatherRestApi.class);

    weatherRestApi = httpClient.get();
  }

  @Override public Observable<WeatherEntity> WeatherEntityByCityId(int cityId) {
    return weatherRestApi.WeatherEntityByCityId(cityId);
  }
}
