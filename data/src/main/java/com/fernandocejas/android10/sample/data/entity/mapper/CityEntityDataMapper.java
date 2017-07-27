package com.fernandocejas.android10.sample.data.entity.mapper;

import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.domain.City;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class CityEntityDataMapper {

  @Inject CityEntityDataMapper() {
  }

  public City transform(CityEntity cityEntity) {
    City city = null;
    if (cityEntity != null) {
      city = new City(cityEntity.getCity(), cityEntity.getId());
    }
    return city;
  }

  //todo to rx!!??
  public List<City> transform(Collection<CityEntity> cityEntityCollection) {
    final List<City> cities = new ArrayList<>();
    for (CityEntity cityEntity : cityEntityCollection) {
      final City city = transform(cityEntity);
      if (city != null) {
        cities.add(city);
      }
    }
    return cities;
  }
}
