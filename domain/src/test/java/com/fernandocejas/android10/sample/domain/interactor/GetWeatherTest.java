package com.fernandocejas.android10.sample.domain.interactor;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.WeatherIn;
import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.logic.WeatherTransformer;
import com.fernandocejas.android10.sample.domain.repository.WeatherRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Ruby on 7/26/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class GetWeatherTest {

  private static final String CITY_ID = "123";

  @InjectMocks private GetWeather getWeather;

  @Mock private WeatherRepository mockWeatherRepository;
  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private WeatherTransformer mockWeatherTransformer;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before public void setUp() {
  }

  @Test public void testGetUserDetailsUseCaseObservableHappyCase() {
    final Observable<Weather> observable = createWeatherObservable();
    final Observable<WeatherIn> observableIn = createWeatherInObservable();
    ObservableTransformer<WeatherIn, Weather> observableTransformer =
        createObservableTransformer(observable);

    when(mockWeatherRepository.weather(CITY_ID)).thenReturn(observableIn);
    when(mockWeatherTransformer.apply()).thenReturn(observableTransformer);

    getWeather.buildUseCaseObservable(GetWeather.Params.forCity(CITY_ID));

    verify(mockWeatherRepository).weather(CITY_ID);
    verifyNoMoreInteractions(mockWeatherRepository);
    verifyZeroInteractions(mockPostExecutionThread);
    verifyZeroInteractions(mockThreadExecutor);

    verify(mockWeatherRepository).weather(CITY_ID);
    verify(mockWeatherTransformer).apply();
  }

  private Observable<WeatherIn> createWeatherInObservable() {
    return new Observable<WeatherIn>() {
      @Override protected void subscribeActual(Observer<? super WeatherIn> observer) {

      }
    };
  }

  @Test public void testShouldFailWhenNoOrEmptyParameters() {
    expectedException.expect(NullPointerException.class);
    getWeather.buildUseCaseObservable(null);
  }

  public ObservableTransformer<WeatherIn, Weather> createObservableTransformer(
      final Observable<Weather> observable) {
    return new ObservableTransformer<WeatherIn, Weather>() {
      @Override public ObservableSource<Weather> apply(@NonNull Observable<WeatherIn> upstream) {
        return observable;
      }
    };
  }

  public Observable<Weather> createWeatherObservable() {
    return new Observable<Weather>() {
      @Override protected void subscribeActual(Observer<? super Weather> observer) {

      }
    };
  }
}
