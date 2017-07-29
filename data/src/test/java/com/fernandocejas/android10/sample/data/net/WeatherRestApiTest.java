package com.fernandocejas.android10.sample.data.net;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import com.fernandocejas.android10.sample.data.net.retrofit.WeatherRestApiI;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ruby on 7/27/2017.
 */
@RunWith(MockitoJUnitRunner.class) public class WeatherRestApiTest {

  private static final String FAKE_CITY_ID = "12345";

  @InjectMocks private WeatherRestApiImpl weatherRestApi;
  @Mock private WeatherRestApiI mockWeatherRestApiI;

  @Before public void setUp() throws Exception {
  }

  @Test public void testWeatherEntityByCityId() {
    WeatherEntity weatherEntity = createWeatherEntity();
    Observable<WeatherEntity> fakeWeatherEntityObs = createObs(weatherEntity);

    when(mockWeatherRestApiI.weatherEntityByCityId(anyString(), anyString(),
        anyString())).thenReturn(fakeWeatherEntityObs);

    assertThat(weatherRestApi.weatherEntityByCityId(FAKE_CITY_ID)).isEqualTo(fakeWeatherEntityObs);

    verify(mockWeatherRestApiI).weatherEntityByCityId(anyString(), anyString(), anyString());
  }

  private Observable<WeatherEntity> createObs(WeatherEntity weatherEntity) {
    return Observable.just(weatherEntity);
  }

  @NonNull private WeatherEntity createWeatherEntity() {
    return new WeatherEntity();
  }
}
