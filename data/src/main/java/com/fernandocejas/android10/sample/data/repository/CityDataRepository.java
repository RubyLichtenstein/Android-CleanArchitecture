package com.fernandocejas.android10.sample.data.repository;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.data.disk.DiskApi;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityDataMapper;
import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.domain.repository.CityRepository;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class CityDataRepository implements CityRepository {

  private final DiskApi diskApi;
  private final CityEntityDataMapper cityEntityDataMapper;

  @Inject public CityDataRepository(@NonNull DiskApi diskApi,
      @NonNull CityEntityDataMapper cityEntityDataMapper) {
    this.diskApi = diskApi;
    this.cityEntityDataMapper = cityEntityDataMapper;
  }

  @Override public Observable<City> cites() {
    return diskApi.cityEntityList().map(cityEntityDataMapper::transform);
  }
}
