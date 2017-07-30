package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.WeatherIn;
import io.reactivex.ObservableTransformer;

/**
 * Created by Ruby on 7/29/2017.
 */

public interface WeatherTransformer {
  ObservableTransformer<WeatherIn, Weather> apply();
}
