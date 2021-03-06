package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.City;
import io.reactivex.ObservableTransformer;
import java.util.List;

/**
 * Created by Ruby on 7/28/2017.
 */

public interface CitySorting {
  ObservableTransformer<City, City> apply();

  ObservableTransformer<List<City>, List<City>> applyList();
}
