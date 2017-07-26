package com.fernandocejas.android10.sample.data.repository;

import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityDataMapper;
import com.fernandocejas.android10.sample.data.repository.datasource.CityDataStore;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by Ruby on 7/26/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class CityDataRepositoryTest {

  private static final String FAKE_CITY_NAME = "London";
  private static final String FAKE_CITY_ID = "1234";

  private CityDataRepository cityDataRepository;

  @Mock private CityDataStore mockCityDataStore;
  @Mock private CityEntityDataMapper mockCityEntityDataMapper;

  @Before public void setUp() {
    cityDataRepository = new CityDataRepository(mockCityEntityDataMapper, mockCityDataStore);
  }

  @Test public void testGetCitiesHappyCase() {
    List<CityEntity> cityEntities = new ArrayList<>();
    cityEntities.add(new CityEntity(FAKE_CITY_NAME, FAKE_CITY_ID));
    given(mockCityDataStore.cityEntityList()).willReturn(Observable.just(cityEntities));

    cityDataRepository.cites();

    verify(mockCityDataStore).cityEntityList();
  }
  //todo need more! test cases
}
