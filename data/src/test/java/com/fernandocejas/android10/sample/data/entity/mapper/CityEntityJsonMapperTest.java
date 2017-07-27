package com.fernandocejas.android10.sample.data.entity.mapper;

import com.fernandocejas.android10.sample.data.entity.CitiesEntity;
import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Ruby on 7/27/2017.
 */

public class CityEntityJsonMapperTest {

  private final String LONDON_ID = "2643743";
  private final String PARIS_ID = "2988507";

  private final String LONDON = "London";
  private final String PARIS = "Paris";

  private final int NUM_OF_CITIES = 5;

  private static final String JSON_CITIES = "{\"cities\":["
      + "{\"city\":\"London\", \"id\":\"2643743\"},"
      + "{\"city\":\"Paris\", \"id\":\"2988507\"},"
      + "{\"city\":\"Istanbul\", \"id\":\"745044\"},"
      + "{\"city\":\"Athens\", \"id\":\"264371\"},"
      + "{\"city\":\"Zurich\", \"id\":\"2657896\"}]}";

  private CityEntityJsonMapper cityEntityJsonMapper;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before public void setUp() {
    cityEntityJsonMapper = new CityEntityJsonMapper();
  }

  @Test public void testTransformCityEntityCollectionHappyCase() {
    Collection<CityEntity> cityEntityCollection =
        cityEntityJsonMapper.transformCitiesEntity(JSON_CITIES);
    CityEntity london = (CityEntity) cityEntityCollection.toArray()[0];
    CityEntity paris = (CityEntity) cityEntityCollection.toArray()[1];

    assertThat(london.getId(), is(LONDON_ID));
    assertThat(london.getCity(), is(LONDON));

    assertThat(paris.getId(), is(PARIS_ID));
    assertThat(paris.getCity(), is(PARIS));

    assertThat(cityEntityCollection.size(), is(NUM_OF_CITIES));
  }

  @Test public void testMapCitiesEntityToCityEntityListHappyCase() {
    CitiesEntity citiesEntity = new CitiesEntity();
    ArrayList<CityEntity> cityEntities = new ArrayList<>();
    cityEntities.add(new CityEntity(LONDON, LONDON_ID));
    cityEntities.add(new CityEntity(PARIS, PARIS_ID));
    citiesEntity.setCities(cityEntities);

    List<CityEntity> mappedCityEntities =
        cityEntityJsonMapper.mapCitiesEntityToCityEntityList(citiesEntity);

    assertThat(mappedCityEntities, is(cityEntities));
  }

  @Test public void testTransformCityEntityNotValidResponse() {
    expectedException.expect(JsonSyntaxException.class);
    cityEntityJsonMapper.transformCitiesEntity("ironman");
  }

  @Test public void testTransformCityEntityCollectionNotValidResponse() {
    expectedException.expect(JsonSyntaxException.class);
    cityEntityJsonMapper.transformCitiesEntity("Tony Stark");
  }
}