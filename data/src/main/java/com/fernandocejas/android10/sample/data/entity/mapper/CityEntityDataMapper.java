package com.fernandocejas.android10.sample.data.entity.mapper;

import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.domain.City;
import io.reactivex.Observable;
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

  public List<City> transform(Collection<CityEntity> cityEntityCollection) {
    return Observable.fromIterable(cityEntityCollection)
        .map(this::transform)
        .toList()
        .blockingGet();
  }
}
