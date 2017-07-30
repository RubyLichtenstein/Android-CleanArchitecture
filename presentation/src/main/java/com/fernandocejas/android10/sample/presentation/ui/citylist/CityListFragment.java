package com.fernandocejas.android10.sample.presentation.ui.citylist;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
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
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.Collection;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017
 */

public class CityListFragment extends BaseFragment implements CityListView {

  @Inject CityListPresenter cityListPresenter;
  @Inject CityListAdapter cityListAdapter;

  @BindView(R.id.rv_cities) RecyclerView rvCities;
  @BindView(R.id.progress_bar) ProgressBar progressBar;

  private Disposable cityClickDisposable;

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

  @TargetApi(23) @Override public void onAttach(Context context) {
    //This method avoid to call super.onAttach(context) if I'm not using api 23 or more
    //if (Build.VERSION.SDK_INT >= 23) {
    super.onAttach(context);
    onAttachToContext(context);
  }

  private void onAttachToContext(Context context) {
    setActivityConsumerCityClick(context);
  }

  /*
   * Deprecated on API 23
   * Use onAttachToContext instead
   */
  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (Build.VERSION.SDK_INT < 23) {
      onAttachToContext(activity);
    }
  }

  private Consumer<CityModel> activityCityClickObs;

  private void setActivityConsumerCityClick(Context context) {
    if (context instanceof CityListActivity) {
      activityCityClickObs = ((CityListActivity) context).getOnCityClickObserver();
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    activityCityClickObs = null;
    unsubsidised();
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.cityListPresenter.setView(this);
    this.cityClickDisposable = getCityClickObs().subscribe(activityCityClickObs);
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

  public void unsubsidised() {
    if (cityClickDisposable != null) {
      cityClickDisposable.dispose();
    }
  }

  @Override public void showLoading(boolean show) {
    this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.rvCities.setLayoutManager(new LinearLayoutManager(context()));
    this.rvCities.setAdapter(cityListAdapter);
  }

  /**
   * Loads all cities.
   */
  private void loadCityList() {
    this.cityListPresenter.initialize();
  }

  @Override public void renderCityList(Collection<CityModel> cityModelCollection) {
    if (cityModelCollection != null) {
      this.cityListAdapter.setCitiesCollection(cityModelCollection);
    }
  }

  @Override public void renderCity(CityModel cityModel) {
    if (cityModel != null) {
      this.cityListAdapter.addCityModel(cityModel);
    }
  }

  //todo not used!
  @Override public void getCityClickObs(CityModel cityModel) {

  }

  public Observable<CityModel> getCityClickObs() {
    return cityListAdapter.getCityClickObs();
  }
}
