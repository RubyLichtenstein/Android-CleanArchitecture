package com.fernandocejas.android10.sample.data.disk;

import com.fernandocejas.android10.sample.data.entity.CityEntity;
import io.reactivex.Observable;

/**
 * Created by Ruby on 7/26/2017.
 */

public interface DiskApi {

  Observable<CityEntity> cityEntityList();
}
