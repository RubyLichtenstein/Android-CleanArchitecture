package com.fernandocejas.android10.sample.presentation.ui.citylist;

import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.ui.base.BaseLoadDataView;
import com.fernandocejas.android10.sample.presentation.ui.base.BasePresenterContract;
import io.reactivex.Observable;

/**
 * Created by Ruby on 7/30/2017.
 */

public interface CityListMvpContract {
  interface View extends BaseLoadDataView {

    void renderCity(CityModel cityModel);

    Observable<CityModel> cityClick();
  }

  interface Presenter extends BasePresenterContract {
    void onCityClick(CityModel cityModel);
  }
}
