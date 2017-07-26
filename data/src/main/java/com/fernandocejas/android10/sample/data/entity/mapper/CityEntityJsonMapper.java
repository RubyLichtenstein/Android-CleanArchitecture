package com.fernandocejas.android10.sample.data.entity.mapper;

import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/26/2017.
 */

public class CityEntityJsonMapper {
  private final Gson gson;

  @Inject
  public CityEntityJsonMapper() {
    this.gson = new Gson();
  }

  public CityEntity transformUserEntity(String cityJson) throws JsonSyntaxException {
    final Type cityEntityType = new TypeToken<CityEntity>() {}.getType();
    return this.gson.fromJson(cityJson, cityEntityType);
  }

  public List<CityEntity> transformCityEntityCollection(String citesJson)
      throws JsonSyntaxException {
    final Type listOfCityEntityType = new TypeToken<List<CityEntity>>() {}.getType();
    return this.gson.fromJson(citesJson, listOfCityEntityType);
  }
}
