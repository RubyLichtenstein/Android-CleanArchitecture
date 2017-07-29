package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import com.fernandocejas.android10.sample.data.net.retrofit.Config;
import com.fernandocejas.android10.sample.data.net.retrofit.WeatherRestApiI;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/28/2017
 */

@Singleton public class WeatherRestApiImpl implements WeatherRestApi {

  private final WeatherRestApiI weatherRestApiI;

  @Inject public WeatherRestApiImpl(WeatherRestApiI weatherRestApiI) {
    this.weatherRestApiI = weatherRestApiI;
  }

  @Override public Observable<WeatherEntity> weatherEntityByCityId(String cityId) {
    return weatherRestApiI.weatherEntityByCityId(cityId, Config.UNITS, Config.APP_ID);
  }
}
