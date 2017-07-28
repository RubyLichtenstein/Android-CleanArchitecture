package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import com.fernandocejas.android10.sample.data.net.retrofit.Config;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/28/2017.
 */

//todo test
@Singleton public class WeatherRestApiImpl implements WeatherRestApi {

  private final WeatherRestApiFactory weatherRestApiFactory;

  @Inject public WeatherRestApiImpl(WeatherRestApiFactory weatherRestApiFactory) {
    this.weatherRestApiFactory = weatherRestApiFactory;
  }

  @Override public Observable<WeatherEntity> WeatherEntityByCityId(int cityId) {
    return weatherRestApiFactory.get().WeatherEntityByCityId(cityId, Config.UNITS, Config.APP_ID);
  }
}
