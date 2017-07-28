package com.fernandocejas.android10.sample.presentation.ui.citylist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.internal.di.components.UserComponent;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.view.adapter.UsersAdapter;
import com.fernandocejas.android10.sample.presentation.view.fragment.BaseFragment;
import java.util.Collection;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

public class CityListFragment extends BaseFragment implements CityListView{

  @Inject CityListPresenter userListPresenter;
  //todo rename
  @Inject UsersAdapter usersAdapter;

  public CityListFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof UserListListener) {
      this.userListListener = (UserListListener) activity;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(UserComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
    ButterKnife.bind(this, fragmentView);
    setupRecyclerView();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.userListPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadUserList();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.userListPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.userListPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    rv_users.setAdapter(null);
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.userListPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.userListListener = null;
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  @Override public void showRetry() {

  }

  @Override public void hideRetry() {

  }

  @Override public void showError(String message) {

  }

  @Override public Context context() {
    return null;
  }

  @Override public void renderCityList(Collection<CityModel> cityModelCollection) {

  }

  @Override public void viewCity(CityModel cityModel) {

  }
}
