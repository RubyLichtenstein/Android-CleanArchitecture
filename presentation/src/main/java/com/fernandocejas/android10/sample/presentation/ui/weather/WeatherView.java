package com.fernandocejas.android10.sample.presentation.ui.weather;

import com.fernandocejas.android10.sample.presentation.ui.base.BaseLoadDataView;
import io.reactivex.Observable;

/**
 * Created by Ruby on 7/28/2017.
 */

public interface WeatherView extends BaseLoadDataView {
  void renderWeather(WeatherViewModel weatherViewModel);

  Observable<Object> celsiusBtnClick();

  Observable<Object> fahrenheitBtnClick();
}
