package com.fernandocejas.android10.sample.data.disk;

import android.support.annotation.NonNull;
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

  @Inject public DiskApiImpl(@NonNull String fileName, @NonNull AssetsReader assetsReader,
      @NonNull CityEntityJsonMapper cityEntityJsonMapper) {
    this.fileName = fileName;
    this.assetsReader = assetsReader;
    this.cityEntityJsonMapper = cityEntityJsonMapper;
  }

  @NonNull @Override public Observable<CityEntity> cityEntityList() {
    return Observable.create(e -> {
      try {
        String citiesJson = assetsReader.readFromAssets(fileName);
        List<CityEntity> cityEntities = cityEntityJsonMapper.transformCitiesEntity(citiesJson);
        for (CityEntity cityEntity : cityEntities) {
          e.onNext(cityEntity);
        }
        e.onComplete();
      } catch (IOException exc) {
        e.onError(exc);
      }
    });
  }
}
