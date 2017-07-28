package com.fernandocejas.android10.sample.domain.interactor;

import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.repository.WeatherRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by Ruby on 7/26/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class GetWeatherTest {

  private static final int CITY_ID = 123;

  private GetWeather getWeather;

  @Mock private WeatherRepository mockWeatherRepository;
  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before public void setUp() {
    getWeather = new GetWeather(mockWeatherRepository, mockThreadExecutor, mockPostExecutionThread,
        weatherTempCalc);
  }

  @Test public void testGetUserDetailsUseCaseObservableHappyCase() {
    getWeather.buildUseCaseObservable(GetWeather.Params.forCity(CITY_ID));

    verify(mockWeatherRepository).weather(CITY_ID);
    verifyNoMoreInteractions(mockWeatherRepository);
    verifyZeroInteractions(mockPostExecutionThread);
    verifyZeroInteractions(mockThreadExecutor);
  }

  @Test public void testShouldFailWhenNoOrEmptyParameters() {
    expectedException.expect(NullPointerException.class);
    getWeather.buildUseCaseObservable(null);
  }
}
