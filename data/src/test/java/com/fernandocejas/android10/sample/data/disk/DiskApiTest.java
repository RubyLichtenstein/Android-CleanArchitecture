package com.fernandocejas.android10.sample.data.disk;

import com.fernandocejas.android10.sample.data.ApplicationTestCase;
import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityJsonMapper;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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
    List<CityEntity> cityEntityList = new ArrayList<>();
    when(mockAssetsReader.readFromAssets(FAKE_FILE_NAME)).thenReturn(FAKE_FILE_CONTENT);
    when(mockCityEntityJsonMapper.transformCitiesEntity(FAKE_FILE_CONTENT)).thenReturn(
        cityEntityList);

    diskApi.cityEntityList().subscribe(cityEntities -> {

    });

    verify(mockAssetsReader).readFromAssets(FAKE_FILE_NAME);
    verify(mockCityEntityJsonMapper).transformCitiesEntity(FAKE_FILE_CONTENT);
  }

  @Test public void cityEntityListThrowExceptionCase() throws IOException {

    when(mockAssetsReader.readFromAssets(FAKE_FILE_NAME)).thenThrow(ioException);

    diskApi.cityEntityList().subscribe(new Observer<List<CityEntity>>() {
      @Override public void onSubscribe(Disposable d) {

      }

      @Override public void onNext(List<CityEntity> value) {
        //todo assert not emit??
      }

      @Override public void onError(Throwable e) {
        assertThat(e).isEqualTo(ioException);
      }

      @Override public void onComplete() {

      }
    });

    //verify(mockAssetsReader).readFromAssets(FAKE_FILE_NAME).th;
    //verify(mockCityEntityJsonMapper).transformCityEntityCollection(FAKE_FILE_CONTENT);
  }
}
