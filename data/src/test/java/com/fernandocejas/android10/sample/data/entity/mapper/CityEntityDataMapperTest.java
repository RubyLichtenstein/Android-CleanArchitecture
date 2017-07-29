package com.fernandocejas.android10.sample.data.entity.mapper;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.domain.City;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

/**
 * Created by Ruby on 7/27/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class CityEntityDataMapperTest {
  private final String LONDON = "London";
  private final String LONDON_ID = "2643743";

  @InjectMocks private CityEntityDataMapper cityEntityDataMapper;

  @Before public void setUp() throws Exception {

  }

  @Test public void testTransformCityEntity() {
    CityEntity cityEntity = createFakeCityEntity();
    City city = cityEntityDataMapper.transform(cityEntity);

    assertThat(city, is(instanceOf(City.class)));
    assertThat(city.getName(), is(LONDON));
    assertThat(city.getId(), is(LONDON_ID));
  }

  @Test public void testTransformCityEntityCollection() {
    CityEntity mockCityEntityOne = mock(CityEntity.class);
    CityEntity mockCityEntityTwo = mock(CityEntity.class);

    List<CityEntity> cityEntityList = new ArrayList<>(5);
    cityEntityList.add(mockCityEntityOne);
    cityEntityList.add(mockCityEntityTwo);

    Collection<City> cityCollection = cityEntityDataMapper.transform(cityEntityList);

    assertThat(cityCollection.toArray()[0], is(instanceOf(City.class)));
    assertThat(cityCollection.toArray()[1], is(instanceOf(City.class)));
    assertThat(cityCollection.size(), is(2));
  }

  @NonNull private CityEntity createFakeCityEntity() {
    return new CityEntity(LONDON, LONDON_ID);
  }
}
