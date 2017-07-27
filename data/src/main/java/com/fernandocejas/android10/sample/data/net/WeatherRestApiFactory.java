package com.fernandocejas.android10.sample.data.net;

/**
 * Created by Ruby on 7/27/2017.
 */

public class WeatherRestApiFactory {
  public WeatherRestApi get(String baseUrl) {
    return new HttpClient<>(baseUrl, WeatherRestApi.class).get();
  }
}
