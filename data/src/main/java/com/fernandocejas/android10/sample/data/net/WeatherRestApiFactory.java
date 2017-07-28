package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.net.retrofit.HttpClient;
import com.fernandocejas.android10.sample.data.net.retrofit.WeatherRestApiI;

/**
 * Created by Ruby on 7/27/2017.
 */

public class WeatherRestApiFactory {
  public WeatherRestApiI get(String baseUrl) {
    return new HttpClient<>(baseUrl, WeatherRestApiI.class).get();
  }
}
