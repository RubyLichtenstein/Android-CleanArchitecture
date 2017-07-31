package com.fernandocejas.android10.sample.domain.repository;

import com.fernandocejas.android10.sample.domain.WeatherRaw;
import io.reactivex.Observable;

/**
 * Created by Ruby on 7/26/2017.
 */

public interface WeatherRepository {
  Observable<WeatherRaw> weather(final String cityId);
}
