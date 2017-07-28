package com.fernandocejas.android10.sample.presentation.ui.citylist;

import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.view.LoadDataView;
import io.reactivex.Observable;
import java.util.Collection;

/**
 * Created by Ruby on 7/28/2017.
 */

public interface CityListView extends LoadDataView {
  /**
   * Render a city list in the UI.
   *
   * @param cityModelCollection The collection of {@link CityModel} that will be shown.
   */
  void renderCityList(Collection<CityModel> cityModelCollection);

  void renderCity(CityModel cityModel);

  /**
   * View a {@link CityModel} name.
   *
   * @param cityModel The city that will be shown.
   */
  void viewWeather(CityModel cityModel);

  Observable<CityModel> viewWeather();
}
