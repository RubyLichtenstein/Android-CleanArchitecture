package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/28/2017.
 */

@Singleton
public class WeatherRestApiImpl implements WeatherRestApi {

  @Inject
  public WeatherRestApiImpl() {
  }

  @Override public Observable<WeatherEntity> WeatherEntityByCityId(int cityId) {
    return null;
  }
}
