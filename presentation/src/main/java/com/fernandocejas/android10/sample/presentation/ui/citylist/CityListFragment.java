package com.fernandocejas.android10.sample.presentation.ui.citylist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.internal.di.components.CityListComponent;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.ui.base.BaseFragment;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017
 */

public class CityListFragment extends BaseFragment implements CityListMvpContract.View {

  @Inject CityListPresenter cityListPresenter;
  @Inject CityListAdapter cityListAdapter;

  @BindView(R.id.rv_cities) RecyclerView rvCities;
  @BindView(R.id.progress_bar) ProgressBar progressBar;

  public CityListFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(CityListComponent.class).inject(this);
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
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.cityListPresenter.destroy();
  }

  @Override public void showLoading(boolean show) {
    this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showError(String s) {
    this.showToastMessage(s);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.rvCities.setLayoutManager(new LinearLayoutManager(context()));
    this.rvCities.setAdapter(cityListAdapter);
  }

  private void loadCityList() {
    this.cityListPresenter.initialize();
  }

  @Override public void renderCity(CityModel cityModel) {
    if (cityModel != null) {
      this.cityListAdapter.addCityModel(cityModel);
    }
  }

  @Override public Observable<CityModel> cityClick() {
    return cityListAdapter.getCityClickObs();
  }
}
