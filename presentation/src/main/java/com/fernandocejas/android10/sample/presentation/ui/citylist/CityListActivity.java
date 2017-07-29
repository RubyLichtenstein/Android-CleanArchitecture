package com.fernandocejas.android10.sample.presentation.ui.citylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.internal.di.HasComponent;
import com.fernandocejas.android10.sample.presentation.internal.di.components.DaggerUserComponent;
import com.fernandocejas.android10.sample.presentation.internal.di.components.UserComponent;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.view.activity.BaseActivity;
import com.fernandocejas.android10.sample.presentation.view.adapter.CitiesLayoutManager;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.Collection;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

public class CityListActivity extends BaseActivity
    implements HasComponent<UserComponent>, CityListView {

  @Inject CityListPresenter cityListPresenter;
  @Inject CitiesAdapter citiesAdapter;

  @BindView(R.id.rv_cities) RecyclerView rvCities;
  @BindView(R.id.progress_bar) ProgressBar progressBar;

  private Disposable cityClickDisposable;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, CityListActivity.class);
  }

  private UserComponent userComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.initializeInjector();
    userComponent.inject(this);
    setContentView(R.layout.city_list_activity);
    ButterKnife.bind(this);
    setupRecyclerView();

    this.cityListPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadCityList();
    }
    //if (savedInstanceState == null) {
    //  addFragment(R.id.fragmentContainer, new CityListFragment());
    //}
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public UserComponent getComponent() {
    return userComponent;
  }

  public void onCityClicked(CityModel cityModel) {
    this.navigator.navigateToWeather(this, cityModel.getId());
  }

  private Consumer<CityModel> onCityClickObs = new Consumer<CityModel>() {
    @Override public void accept(CityModel cityModel) throws Exception {
      onCityClicked(cityModel);
    }
  };

  public Consumer<CityModel> getOnCityClickObserver() {
    return onCityClickObs;
  }


  //@Override public void onCreate(Bundle savedInstanceState) {
  //  super.onCreate(savedInstanceState);
  //  this.getComponent(UserComponent.class).inject(this);
  //}
  //
  //@Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
  //    Bundle savedInstanceState) {
  //  final View fragmentView = inflater.inflate(R.layout.city_list_fragment, container, false);
  //  ButterKnife.bind(this, fragmentView);
  //  setupRecyclerView();
  //  return fragmentView;
  //}
  //
  //@Override public void onAttach(Context context) {
  //  super.onAttach(context);
  //}

  void setActivityObserveCityClick(Context context) {
    if (context instanceof CityListActivity) {
      Consumer<CityModel> cityClickObs = ((CityListActivity) context).getOnCityClickObserver();
      cityClickDisposable = cityClickObs().subscribe(cityClickObs);
    }
  }
  //
  //@Override public void onDetach() {
  //  super.onDetach();
  //
  //}

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }


  @Override public void onResume() {
    super.onResume();
    this.cityListPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.cityListPresenter.pause();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    rvCities.setAdapter(null);
    this.cityListPresenter.destroy();
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
    return this.getApplicationContext();
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
    setActivityObserveCityClick(this);
  }

  @Override public void renderCityList(Collection<CityModel> cityModelCollection) {
    if (cityModelCollection != null) {
      this.citiesAdapter.setCitiesCollection(cityModelCollection);
    }
  }

  @Override public void renderCity(CityModel cityModel) {
    if (cityModel != null) {
      this.citiesAdapter.addCityModel(cityModel);
    }
  }

  @Override public Observable<CityModel> cityClickObs() {
    return citiesAdapter.getCityClickObs();
  }
}
