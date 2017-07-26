package com.fernandocejas.android10.sample.data.disk;

import com.fernandocejas.android10.sample.data.ApplicationTestCase;
import com.fernandocejas.android10.sample.data.entity.CityEntity;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityJsonMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

  @Before public void setUp() throws IOException {
    diskApi = new DiskApiImpl(FAKE_FILE_NAME, mockAssetsReader, mockCityEntityJsonMapper);
    List<CityEntity> cityEntityList = new ArrayList<>();

    when(mockAssetsReader.readFromAssets(FAKE_FILE_NAME)).thenReturn(FAKE_FILE_CONTENT);
    when(mockCityEntityJsonMapper.transformCityEntityCollection(FAKE_FILE_CONTENT)).thenReturn(
        cityEntityList);
  }

  @Test public void cityEntityListHappyCase() throws IOException {
    diskApi.cityEntityList().subscribe(cityEntities -> {

    });

    verify(mockAssetsReader).readFromAssets(FAKE_FILE_NAME);
    verify(mockCityEntityJsonMapper).transformCityEntityCollection(FAKE_FILE_CONTENT);
  }
}
