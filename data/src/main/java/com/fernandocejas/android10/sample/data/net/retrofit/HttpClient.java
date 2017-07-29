package com.fernandocejas.android10.sample.data.net.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ruby on 7/27/2017
 */

class HttpClient<T> {
  private T client;

  public HttpClient(String baseUrl, final Class<T> service) {
    Gson gson = new GsonBuilder().create();
    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    client = new Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .callFactory(httpClientBuilder.build())
        .build()
        .create(service);
  }

  public T get() {
    return client;
  }
}
