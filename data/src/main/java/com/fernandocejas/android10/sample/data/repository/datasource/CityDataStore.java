package com.fernandocejas.android10.sample.data.repository.datasource;

import com.fernandocejas.android10.sample.data.entity.CityEntity;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by Ruby on 7/26/2017.
 */

public interface CityDataStore {
  Observable<List<CityEntity>> cityEntityList();
}
