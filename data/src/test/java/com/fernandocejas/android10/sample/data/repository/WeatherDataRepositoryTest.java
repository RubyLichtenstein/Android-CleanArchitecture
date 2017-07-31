package com.fernandocejas.android10.sample.data.repository;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import com.fernandocejas.android10.sample.data.entity.mapper.WeatherEntityDataMapper;
import com.fernandocejas.android10.sample.data.net.WeatherRestApi;
import com.fernandocejas.android10.sample.domain.WeatherRaw;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
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
 * Created by Ruby on 7/27/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class WeatherDataRepositoryTest {
  private static final String FAKE_CITY_ID = "1234";

  @InjectMocks private WeatherDataRepository weatherDataRepository;

  @Mock private WeatherRestApi mockWeatherRestApi;
  @Mock private WeatherEntityDataMapper mockWeatherEntityDataMapper;

  @Before public void setUp() {

  }

  @Test public void testGetWeatherHappyCase() {
    WeatherEntity weatherEntity = new WeatherEntity();
    WeatherRaw weather = new WeatherRaw();

    given(mockWeatherRestApi.weatherEntityByCityId(FAKE_CITY_ID)).willReturn(
        Observable.just(weatherEntity));
    given(mockWeatherEntityDataMapper.transform(weatherEntity)).willReturn(weather);

    TestObserver<WeatherRaw> testObserver = new TestObserver<>();
    weatherDataRepository.weather(FAKE_CITY_ID).subscribe(testObserver);

    assertThat(testObserver.values().get(0)).isEqualTo(weather);

    verify(mockWeatherRestApi).weatherEntityByCityId(FAKE_CITY_ID);
    verify(mockWeatherEntityDataMapper).transform(weatherEntity);
  }
}
