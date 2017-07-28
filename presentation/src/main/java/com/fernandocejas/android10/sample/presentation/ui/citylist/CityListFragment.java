package com.fernandocejas.android10.sample.presentation.ui.citylist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.interactor.GetWeather;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.internal.di.components.UserComponent;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.view.adapter.CitiesLayoutManager;
import com.fernandocejas.android10.sample.presentation.view.fragment.BaseFragment;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import java.util.Collection;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

public class CityListFragment extends BaseFragment implements CityListView {

  @Inject GetWeather getWeatherUseCase;
  @Inject CityListPresenter cityListPresenter;
  @Inject CitiesAdapter citiesAdapter;

  @Bind(R.id.rv_cities) RecyclerView rvCities;
  @Bind(R.id.progress_bar) ProgressBar progressBar;

  public CityListFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(UserComponent.class).inject(this);

    getWeatherUseCase.execute(new DisposableObserver<Weather>() {
      @Override public void onNext(Weather value) {
        Log.d("TAG", "onNext: " + value.toString());
      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onComplete() {

      }
    }, GetWeather.Params.forCity(2643743));

  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.city_list_fragment, container, false);
    ButterKnife.bind(this, fragmentView);
    setupRecyclerView();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.cityListPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadCityList();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.cityListPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.cityListPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    rvCities.setAdapter(null);
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.cityListPresenter.destroy();
  }

  @Override public void showLoading() {
    this.progressBar.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override public void hideLoading() {
    this.progressBar.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
  }

  @Override public void showRetry() {
  }

  @Override public void hideRetry() {
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.rvCities.setLayoutManager(new CitiesLayoutManager(context()));
    this.rvCities.setAdapter(citiesAdapter);
  }

  /**
   * Loads all cities.
   */
  private void loadCityList() {
    this.cityListPresenter.initialize();
  }

  @Override public void renderCityList(Collection<CityModel> cityModelCollection) {
    if (cityModelCollection != null) {
      this.citiesAdapter.setCitiesCollection(cityModelCollection);
    }
  }

  @Override public void viewWeather(CityModel cityModel) {

  }

  @Override public Observable<CityModel> viewWeather() {
    return citiesAdapter.getCityClickObs();
  }
}
