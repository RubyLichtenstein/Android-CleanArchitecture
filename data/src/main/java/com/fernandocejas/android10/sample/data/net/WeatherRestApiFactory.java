package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.net.retrofit.HttpClient;
import com.fernandocejas.android10.sample.data.net.retrofit.WeatherRestApiI;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/27/2017.
 */

@Singleton public class WeatherRestApiFactory {
  private String baseUrl;

  @Inject public WeatherRestApiFactory(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Inject public WeatherRestApiI get() {
    return new HttpClient<>(baseUrl, WeatherRestApiI.class).get();
  }
}
