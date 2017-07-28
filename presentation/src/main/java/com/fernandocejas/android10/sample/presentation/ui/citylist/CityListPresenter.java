package com.fernandocejas.android10.sample.presentation.ui.citylist;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.domain.interactor.GetCites;
import com.fernandocejas.android10.sample.presentation.exception.ErrorMessageFactory;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.mapper.CityModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;
import io.reactivex.functions.Consumer;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

@PerActivity public class CityListPresenter implements Presenter {

  private CityListView cityListView;

  private final GetCites getCitesUseCase;
  private final CityModelDataMapper cityModelDataMapper;

  @Inject
  public CityListPresenter(GetCites getCitesUseCase, CityModelDataMapper cityModelDataMapper) {
    this.getCitesUseCase = getCitesUseCase;
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
    this.getCitesUseCase.dispose();
    this.cityListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the city list.
   */
  public void initialize() {
    this.loadCityList();
    //todo dispose
    this.cityListView.viewWeather().subscribe(new Consumer<CityModel>() {
      @Override public void accept(CityModel cityModel) throws Exception {
        CityListPresenter.this.cityListView.viewWeather(cityModel);
      }
    });
  }

  private void loadCityList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getCityList();
  }

  public void onCityClicked(CityModel cityModel) {
    this.cityListView.viewWeather(cityModel);
  }

  private void showViewLoading() {
    this.cityListView.showLoading();
  }

  private void hideViewLoading() {
    this.cityListView.hideLoading();
  }

  private void showViewRetry() {
    this.cityListView.showRetry();
  }

  private void hideViewRetry() {
    this.cityListView.hideRetry();
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

  private void getCityList() {
    this.getCitesUseCase.execute(new CityListObserver(), null);
  }

  private final class CityListObserver extends DefaultObserver<List<City>> {

    @Override public void onComplete() {
      CityListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      CityListPresenter.this.hideViewLoading();
      CityListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      CityListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<City> cities) {
      CityListPresenter.this.showCityCollectionInView(cities);
    }
  }
}
