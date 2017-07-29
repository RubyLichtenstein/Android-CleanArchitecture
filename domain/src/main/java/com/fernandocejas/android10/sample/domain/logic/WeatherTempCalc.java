package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.Weather;
import io.reactivex.ObservableTransformer;

/**
 * Created by Ruby on 7/29/2017.
 */

public interface WeatherTempCalc {
  ObservableTransformer<Weather, Weather> apply();
}
