package com.fernandocejas.android10.sample.data.repository.datasource;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.data.disk.DiskApi;
import com.fernandocejas.android10.sample.data.entity.CityEntity;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by Ruby on 7/26/2017.
 */

public class DiskCityDataStore implements CityDataStore {

  private final DiskApi diskApi;

  //todo @Inject
  public DiskCityDataStore(@NonNull DiskApi diskApi) {
    //todo null check?
    this.diskApi = diskApi;
  }

  @Override public Observable<List<CityEntity>> cityEntityList() {
    return diskApi.cityEntityList();
  }
}
