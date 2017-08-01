package com.fernandocejas.android10.sample.test.mapper;

import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.presentation.mapper.CityModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ruby on 7/28/2017.
 */

public class CityModelDataMapperTest extends TestCase {

  private static final String FAKE_CITY_NAME = "London";
  private static final String FAKE_CITY_ID = "1234";

  private CityModelDataMapper cityModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    cityModelDataMapper = new CityModelDataMapper();
  }

  public void testTransformCity() {
    City city = createFakeCity();
    CityModel cityModel = cityModelDataMapper.transform(city);

    assertThat(cityModel, is(instanceOf(CityModel.class)));
    assertThat(cityModel.getId(), is(FAKE_CITY_ID));
    assertThat(cityModel.getName(), is(FAKE_CITY_NAME));
  }

  public void testTransformCityCollection() {
    City mockCityOne = createFakeCity();
    City mockCityTwo = createFakeCity();

    List<City> cityList = new ArrayList<>(5);
    cityList.add(mockCityOne);
    cityList.add(mockCityTwo);

    Collection<CityModel> cityModelList = cityModelDataMapper.transform(cityList);

    assertThat(cityModelList.toArray()[0], is(instanceOf(CityModel.class)));
    assertThat(cityModelList.toArray()[1], is(instanceOf(CityModel.class)));
    assertThat(cityModelList.size(), is(2));
  }

  private City createFakeCity() {
    return new City(FAKE_CITY_NAME, FAKE_CITY_ID);
  }
}
