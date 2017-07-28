package com.fernandocejas.android10.sample.presentation.mapper;

import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.Collection;
import java.util.Collections;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

@PerActivity public class CityModelDataMapper {

  @Inject public CityModelDataMapper() {
  }

  public CityModel transform(City city) {
    if (city == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }

    return new CityModel(city.getName(), city.getId());
  }

  public Collection<CityModel> transform(Collection<City> citysCollection) {
    Collection<CityModel> cityModelsCollection;

    if (citysCollection != null && !citysCollection.isEmpty()) {
      cityModelsCollection =
          Observable.fromIterable(citysCollection).map(new Function<City, CityModel>() {
            @Override public CityModel apply(City city) throws Exception {
              return transform(city);
            }
          }).toList().blockingGet();
    } else {
      cityModelsCollection = Collections.emptyList();
    }

    return cityModelsCollection;
  }
}
