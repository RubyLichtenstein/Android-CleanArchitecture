package com.fernandocejas.android10.sample.data.disk;

import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityJsonMapper;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton public class DiskApiImpl implements DiskApi {

  private final String fileName;
  private final AssetsReader assetsReader;
  private final CityEntityJsonMapper cityEntityJsonMapper;

  @Inject public DiskApiImpl(String fileName, AssetsReader assetsReader,
      CityEntityJsonMapper cityEntityJsonMapper) {

    //todo test nulp
    // TODO: 7/26/2017 use predictions? or nonull
    if (cityEntityJsonMapper == null || assetsReader == null) {
      // TODO: 7/26/2017 to string resource!
      throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
    }

    this.fileName = fileName;
    this.assetsReader = assetsReader;
    this.cityEntityJsonMapper = cityEntityJsonMapper;
  }

  @Override public Observable<List<CityEntity>> cityEntityList() {
    return Observable.create(e -> {
      String citiesJson = null;
      try {
        citiesJson = assetsReader.readFromAssets(fileName);
      } catch (IOException exc) {
        e.onError(exc);
      }
      List<CityEntity> cityEntities = cityEntityJsonMapper.transformCitiesEntity(citiesJson);
      e.onNext(cityEntities);
      e.onComplete();
    });
  }
}
