package com.fernandocejas.android10.sample.data.disk;

import android.content.Context;
import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityJsonMapper;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by Ruby on 7/26/2017.
 */

public class DiskApiImpl implements DiskApi {

  private final AssetsReader assetsReader;
  private final CityEntityJsonMapper cityEntityJsonMapper;

  //todo @Inject
  public DiskApiImpl(AssetsReader assetsReader, CityEntityJsonMapper cityEntityJsonMapper) {
    // TODO: 7/26/2017 use predictions? or nonull
    if (cityEntityJsonMapper == null || assetsReader == null) {
      // TODO: 7/26/2017 to string resource!
      throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
    }

    this.assetsReader = assetsReader;
    this.cityEntityJsonMapper = cityEntityJsonMapper;
  }

  @Override public Observable<List<CityEntity>> cityEntityList() {
    return Observable.create(e -> {
      //todo check what haapen with the excption!
      String citiesJson = assetsReader.readFromAssets(this.context, CITIES_FILE_NAME);
      cityEntityJsonMapper.transformCityEntityCollection(citiesJson);
    });
  }
}
