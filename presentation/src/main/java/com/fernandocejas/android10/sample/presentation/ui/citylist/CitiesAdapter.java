/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.ui.citylist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adaptar that manages a collection of {@link CityModel}.
 */
public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

  //todo deispose!
  private PublishSubject<CityModel> onItemClickSubject;

  private List<CityModel> citiesCollection;
  private final LayoutInflater layoutInflater;

  @Inject CitiesAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.citiesCollection = Collections.emptyList();
    onItemClickSubject = PublishSubject.create();
  }

  @Override public int getItemCount() {
    return (this.citiesCollection != null) ? this.citiesCollection.size() : 0;
  }

  @Override public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.city_list_row, parent, false);
    return new CityViewHolder(view);
  }

  @Override public void onBindViewHolder(CityViewHolder holder, final int position) {
    final CityModel cityModel = this.citiesCollection.get(position);
    holder.textViewTitle.setText(cityModel.getName());
    //todo dispose!?
    RxView.clicks(holder.itemView).subscribe(new Consumer<Object>() {
      @Override public void accept(Object o) throws Exception {
        CitiesAdapter.this.onItemClickSubject.onNext(cityModel);
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setCitiesCollection(Collection<CityModel> citiesCollection) {
    this.validateCitiesCollection(citiesCollection);
    this.citiesCollection = (List<CityModel>) citiesCollection;
    this.notifyDataSetChanged();
  }

  private void validateCitiesCollection(Collection<CityModel> cityModelCollection) {
    if (cityModelCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class CityViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.title) TextView textViewTitle;

    CityViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public Observable<CityModel> getCityClickObs() {
    return onItemClickSubject;
  }
}
