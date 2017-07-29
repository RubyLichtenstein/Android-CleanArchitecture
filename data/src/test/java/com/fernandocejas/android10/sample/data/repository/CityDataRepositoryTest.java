package com.fernandocejas.android10.sample.data.repository;

import com.fernandocejas.android10.sample.data.disk.DiskApi;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityDataMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Ruby on 7/26/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class CityDataRepositoryTest {

  private static final String FAKE_CITY_NAME = "London";
  private static final String FAKE_CITY_ID = "1234";

  @InjectMocks private CityDataRepository cityDataRepository;

  @Mock private DiskApi mockDiskApi;
  @Mock private CityEntityDataMapper mockCityEntityDataMapper;

  @Before public void setUp() {
  }

  @Test public void testGetCitiesHappyCase() {
    //todo fix!
    //List<CityEntity> cityEntities = new ArrayList<>();
    //cityEntities.add(new CityEntity(FAKE_CITY_NAME, FAKE_CITY_ID));
    //given(mockDiskApi.cityEntityList()).willReturn(Observable.just(cityEntities));
    //
    //cityDataRepository.cites();
    //
    //verify(mockDiskApi).cityEntityList();
  }
  //todo need more! test cases?
}
