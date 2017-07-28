package com.fernandocejas.android10.sample.presentation.ui.weather;

import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import com.fernandocejas.android10.sample.presentation.view.LoadDataView;

/**
 * Created by Ruby on 7/28/2017.
 */

public interface WeatherView extends LoadDataView {
  void renderWeather(WeatherModel weatherModel);
}
