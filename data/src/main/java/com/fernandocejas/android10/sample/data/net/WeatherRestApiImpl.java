package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import io.reactivex.Observable;

/**
 * Created by Ruby on 7/27/2017.
 */

public class WeatherRestApiImpl implements WeatherRestApiInterface {
  private final String baseUrl;
  private WeatherRestApiInterface weatherRestApi;

  public WeatherRestApiImpl(String baseUrl) {
    this.baseUrl = baseUrl;

    //todo inject?
    HttpClient<WeatherRestApiInterface> httpClient =
        new HttpClient<>(baseUrl, WeatherRestApiInterface.class);

    weatherRestApi = httpClient.get();
  }

  @Override public Observable<WeatherEntity> WeatherEntityByCityId(int cityId) {
    return weatherRestApi.WeatherEntityByCityId(cityId);
  }
}
