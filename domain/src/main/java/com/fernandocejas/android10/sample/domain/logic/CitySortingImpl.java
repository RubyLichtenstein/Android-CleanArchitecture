package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.City;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ruby on 7/28/2017.
 */

//todo move to its won module!!
public class CitySortingImpl implements CitySorting {
  private final Comparator<City> sortFunction;

  public CitySortingImpl() {
    this.sortFunction = new Comparator<City>() {
      @Override public int compare(City t, City t1) {
        return t.getName().compareTo(t1.getName());
      }
    };
  }

  @Override public ObservableTransformer<City, City> apply() {
    return new ObservableTransformer<City, City>() {
      @Override public ObservableSource<City> apply(@NonNull Observable<City> upstream) {
        return upstream.sorted(sortFunction);
      }
    };
  }

  @Override public ObservableTransformer<List<City>, List<City>> applyList() {
    return new ObservableTransformer<List<City>, List<City>>() {
      @Override
      public ObservableSource<List<City>> apply(@NonNull Observable<List<City>> upstream) {
        return upstream.doOnNext(new Consumer<List<City>>() {
          @Override public void accept(List<City> cities) throws Exception {
            Collections.sort(cities, sortFunction);
          }
        });
      }
    };
  }
}
