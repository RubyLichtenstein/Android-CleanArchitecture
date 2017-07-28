package com.fernandocejas.android10.sample.data.entity.mapper;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.data.entity.CitiesEntity;
import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/26/2017.
 */

@Singleton
public class CityEntityJsonMapper {
  private final Gson gson;

  @Inject public CityEntityJsonMapper() {
    this.gson = new Gson();
  }

  public List<CityEntity> transformCitiesEntity(@NonNull String citiesJson) throws JsonSyntaxException {
    final Type citiesEntityType = new TypeToken<CitiesEntity>() {
    }.getType();
    CitiesEntity citiesEntity = this.gson.fromJson(citiesJson, citiesEntityType);
    return mapCitiesEntityToCityEntityList(citiesEntity);
  }

  public List<CityEntity> mapCitiesEntityToCityEntityList(CitiesEntity citiesEntity) {
    return citiesEntity.getCities();
  }
}
