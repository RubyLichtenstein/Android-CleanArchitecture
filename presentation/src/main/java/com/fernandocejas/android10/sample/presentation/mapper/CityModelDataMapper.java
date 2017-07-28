package com.fernandocejas.android10.sample.presentation.mapper;

import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */

@PerActivity
public class CityModelDataMapper {

  @Inject
  public CityModelDataMapper() {}
 
  public CityModel transform(City city) {
    // TODO: 7/28/2017
    if (city == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    final CityModel cityModel = new CityModel();
    //cityModel.setCoverUrl(city.getCoverUrl());
    //cityModel.setFullName(city.getFullName());
    //cityModel.setEmail(city.getEmail());
    //cityModel.setDescription(city.getDescription());
    //cityModel.setFollowers(city.getFollowers());

    return cityModel;
  }

  // TODO: 7/28/2017
  public Collection<CityModel> transform(Collection<City> citysCollection) {
    Collection<CityModel> cityModelsCollection;

    if (citysCollection != null && !citysCollection.isEmpty()) {
      cityModelsCollection = new ArrayList<>();
      for (City city : citysCollection) {
        cityModelsCollection.add(transform(city));
      }
    } else {
      cityModelsCollection = Collections.emptyList();
    }

    return cityModelsCollection;
  }

}
