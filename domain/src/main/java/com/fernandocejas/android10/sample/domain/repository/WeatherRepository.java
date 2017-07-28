package com.fernandocejas.android10.sample.domain.repository;

import com.fernandocejas.android10.sample.domain.Weather;
import io.reactivex.Observable;

/**
 * Created by Ruby on 7/26/2017.
 */

public interface WeatherRepository {
  Observable<Weather> weather(final String cityId);
}
