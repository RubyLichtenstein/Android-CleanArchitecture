package com.fernandocejas.android10.sample.data.disk;

import com.fernandocejas.android10.sample.data.ApplicationTestCase;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityJsonMapper;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.fernandocejas.android10.sample.data.disk.DiskApi.CITIES_FILE_NAME;
import static org.mockito.Mockito.verify;

/**
 * Created by Ruby on 7/26/2017
 */

@RunWith(MockitoJUnitRunner.class) public class DiskApiTest extends ApplicationTestCase {
  @InjectMocks private DiskApiImpl diskApi;

  @Mock private AssetsReader mockAssetsReader;
  @Mock private CityEntityJsonMapper mockCityEntityJsonMapper;

  @Before public void setUp() throws IOException {
    //inputStream = new ByteArrayInputStream(FAKE_FILE_CONTENT.getBytes());
    //
    //when(mockAssetManager.open(FAKE_FILE_NAME)).thenReturn(inputStream);
    //when(mockFileManager.read(inputStream)).thenReturn(FAKE_FILE_CONTENT);
  }

  @Test public void cityEntityListHappyCase() {

    verify(mockAssetsReader).readFromAssets(inputStream);
    verify(mockCityEntityJsonMapper).transformCityEntityCollection(FAKE_FILE_NAME);
    //String citiesJson = mockAssetsReader.readFromAssets(CITIES_FILE_NAME);
    //mockCityEntityJsonMapper.transformCityEntityCollection(citiesJson);
  }
}
