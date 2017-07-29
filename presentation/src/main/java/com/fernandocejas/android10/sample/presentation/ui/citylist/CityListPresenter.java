package com.fernandocejas.android10.sample.presentation.ui.citylist;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.domain.interactor.GetCityList;
import com.fernandocejas.android10.sample.presentation.exception.ErrorMessageFactory;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.mapper.CityModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.ui.base.Presenter;
import io.reactivex.functions.Consumer;
import java.util.Collection;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

@PerActivity public class CityListPresenter implements Presenter {

  private CityListView cityListView;

  private final GetCityList getCityListUseCase;
  private final CityModelDataMapper cityModelDataMapper;

  @Inject public CityListPresenter(GetCityList getCityListUseCase,
      CityModelDataMapper cityModelDataMapper) {
    this.getCityListUseCase = getCityListUseCase;
    this.cityModelDataMapper = cityModelDataMapper;
  }

  public void setView(@NonNull CityListView view) {
    this.cityListView = view;
  }

  @Override public void resume() {

  }

  @Override public void pause() {

  }

  @Override public void destroy() {
    this.getCityListUseCase.dispose();
    this.cityListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the city list.
   */
  public void initialize() {
    this.loadCityList();
    this.subscribeToViewWeather();
  }

  //todo rename
  private void subscribeToViewWeather() {
    //todo dispose
    this.cityListView.getCityClickObs().subscribe(new Consumer<CityModel>() {
      @Override public void accept(CityModel cityModel) throws Exception {
        CityListPresenter.this.cityListView.getCityClickObs(cityModel);
      }
    });
  }

  private void loadCityList() {
    this.showViewLoading();
    this.getCityList();
  }

  private void showViewLoading() {
    this.cityListView.showLoading(true);
  }

  private void hideViewLoading() {
    this.cityListView.showLoading(false);
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage =
        ErrorMessageFactory.create(this.cityListView.context(), errorBundle.getException());
    this.cityListView.showError(errorMessage);
  }

  private void showCityCollectionInView(Collection<City> citysCollection) {
    final Collection<CityModel> cityModelsCollection =
        this.cityModelDataMapper.transform(citysCollection);
    this.cityListView.renderCityList(cityModelsCollection);
  }

  private void showCityInView(City city) {
    final CityModel cityModels = this.cityModelDataMapper.transform(city);
    this.cityListView.renderCity(cityModels);
  }

  private void getCityList() {
    this.getCityListUseCase.execute(new CityListObserver(), null);
  }

  private final class CityListObserver extends DefaultObserver<City> {

    @Override public void onComplete() {
      CityListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      CityListPresenter.this.hideViewLoading();
      CityListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
    }

    @Override public void onNext(City city) {
      CityListPresenter.this.showCityInView(city);
    }
  }
}
