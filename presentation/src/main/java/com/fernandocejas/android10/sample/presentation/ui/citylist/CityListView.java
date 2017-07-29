package com.fernandocejas.android10.sample.presentation.ui.citylist;

import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.view.LoadDataView;
import io.reactivex.Observable;
import java.util.Collection;

/**
 * Created by Ruby on 7/28/2017.
 */

public interface CityListView extends LoadDataView {

  void renderCityList(Collection<CityModel> cityModelCollection);

  void renderCity(CityModel cityModel);

  Observable<CityModel> cityClickObs();
}
