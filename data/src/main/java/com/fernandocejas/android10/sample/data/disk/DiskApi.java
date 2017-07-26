package com.fernandocejas.android10.sample.data.disk;

import com.fernandocejas.android10.sample.data.entity.CityEntity;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by Ruby on 7/26/2017.
 */

public interface DiskApi {
  //todo maby inject?
  String CITIES_FILE_NAME = "cities.txt";

  Observable<List<CityEntity>> cityEntityList();
}
