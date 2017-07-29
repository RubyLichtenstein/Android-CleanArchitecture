package com.fernandocejas.android10.sample.data.disk;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.data.ApplicationTestCase;
import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityJsonMapper;
import io.reactivex.observers.TestObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ruby on 7/26/2017
 */

@RunWith(MockitoJUnitRunner.class) public class DiskApiTest extends ApplicationTestCase {
  final String FAKE_FILE_NAME = "fake_name";
  final String FAKE_FILE_CONTENT = "";

  private final String CITY = "London";
  private final String CITY_ID = "1234";

  private DiskApiImpl diskApi;

  @Mock private AssetsReader mockAssetsReader;
  @Mock private CityEntityJsonMapper mockCityEntityJsonMapper;

  private IOException ioException;

  @Before public void setUp() throws IOException {
    diskApi = new DiskApiImpl(FAKE_FILE_NAME, mockAssetsReader, mockCityEntityJsonMapper);
    ioException = new IOException();
  }

  //todo test null pointer exception
  @Test public void cityEntityListHappyCase() throws IOException {
    List<CityEntity> cityEntityToEmit = createCityEntities();
    when(mockAssetsReader.readFromAssets(FAKE_FILE_NAME)).thenReturn(FAKE_FILE_CONTENT);
    when(mockCityEntityJsonMapper.transformCitiesEntity(FAKE_FILE_CONTENT)).thenReturn(
        cityEntityToEmit);

    TestObserver<CityEntity> testObserver = new TestObserver<>();
    diskApi.cityEntityList().subscribe(testObserver);
    testObserver.assertComplete();
    testObserver.assertNoErrors();

    assertThat(testObserver.values().get(0)).isEqualTo(cityEntityToEmit);
    assertThat(testObserver.valueCount()).isEqualTo(1);

    verify(mockAssetsReader).readFromAssets(FAKE_FILE_NAME);
    verify(mockCityEntityJsonMapper).transformCitiesEntity(FAKE_FILE_CONTENT);
  }

  @NonNull private List<CityEntity> createCityEntities() {
    return new ArrayList<CityEntity>() {{
      add(new CityEntity(CITY, CITY_ID));
    }};
  }

  @Test public void cityEntityListThrowExceptionCase() throws IOException {

    when(mockAssetsReader.readFromAssets(FAKE_FILE_NAME)).thenThrow(ioException);

    TestObserver<CityEntity> testObserver = new TestObserver<>();
    diskApi.cityEntityList().subscribe(testObserver);

    testObserver.assertError(ioException);
    testObserver.assertNotComplete();
  }
}
