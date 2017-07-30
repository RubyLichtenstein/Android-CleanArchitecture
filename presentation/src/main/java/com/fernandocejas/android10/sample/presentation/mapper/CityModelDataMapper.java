package com.fernandocejas.android10.sample.presentation.mapper;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import io.reactivex.Observable;
import java.util.Collection;
import java.util.Collections;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

@PerActivity public class CityModelDataMapper {

  @Inject public CityModelDataMapper() {
  }

  public CityModel transform(@NonNull City city) {
    return new CityModel(city.getName(), city.getId());
  }

  public Collection<CityModel> transform(Collection<City> citysCollection) {
    Collection<CityModel> cityModelsCollection;

    if (citysCollection != null && !citysCollection.isEmpty()) {
      cityModelsCollection =
          Observable.fromIterable(citysCollection).map(this::transform).toList().blockingGet();
    } else {
      cityModelsCollection = Collections.emptyList();
    }

    return cityModelsCollection;
  }
}
