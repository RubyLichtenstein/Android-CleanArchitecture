package com.fernandocejas.android10.sample.data.repository;

import com.fernandocejas.android10.sample.data.disk.DiskApi;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityDataMapper;
import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.domain.repository.CityRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class CityDataRepository implements CityRepository {

  private final DiskApi diskApi;
  private final CityEntityDataMapper cityEntityDataMapper;

  @Inject public CityDataRepository(DiskApi diskApi, CityEntityDataMapper cityEntityDataMapper) {
    //todo null check or nonull
    this.diskApi = diskApi;
    this.cityEntityDataMapper = cityEntityDataMapper;
  }

  @Override public Observable<List<City>> cites() {
    return diskApi.cityEntityList().map(cityEntityDataMapper::transform);
  }
}
