package com.fernandocejas.android10.sample.data.repository;

import com.fernandocejas.android10.sample.data.disk.DiskApi;
import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityDataMapper;
import com.fernandocejas.android10.sample.domain.City;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

  @Test public void testGetCityListHappyCase() {
    List<CityEntity> cityEntities = new ArrayList<>();
    CityEntity cityEntity = new CityEntity(FAKE_CITY_NAME, FAKE_CITY_ID);
    cityEntities.add(cityEntity);

    City city = new City(FAKE_CITY_NAME, FAKE_CITY_ID);

    given(mockDiskApi.cityEntityList()).willReturn(Observable.fromIterable(cityEntities));
    given(mockCityEntityDataMapper.transform(cityEntity)).willReturn(city);

    TestObserver<City> testObserver = new TestObserver<>();
    cityDataRepository.cites().subscribe(testObserver);

    assertThat(testObserver.values().get(0)).isEqualTo(city);

    verify(mockDiskApi).cityEntityList();
    verify(mockCityEntityDataMapper).transform(cityEntity);
  }
}
