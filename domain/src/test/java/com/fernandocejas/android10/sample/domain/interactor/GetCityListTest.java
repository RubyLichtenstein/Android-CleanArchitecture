package com.fernandocejas.android10.sample.domain.interactor;

import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.logic.CitySorting;
import com.fernandocejas.android10.sample.domain.repository.CityRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Ruby on 7/26/2017.
 */
@RunWith(MockitoJUnitRunner.class) public class GetCityListTest {

  @InjectMocks private GetCityList getCityList;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private CityRepository mockCityRepository;
  @Mock private CitySorting mockCitySorting;

  @Before public void setUp() {
  }

  @Test public void testGetUserListUseCaseObservableHappyCase() {
    final Observable<City> observable = createCityObservable();
    ObservableTransformer<City, City> value = createObservableTransformer(observable);

    when(mockCityRepository.cites()).thenReturn(observable);
    when(mockCitySorting.apply()).thenReturn(value);

    getCityList.buildUseCaseObservable(null);

    verify(mockCityRepository).cites();
    verify(mockCitySorting).apply();
    verifyNoMoreInteractions(mockCityRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }

  public ObservableTransformer<City, City> createObservableTransformer(
      final Observable<City> observable) {
    return new ObservableTransformer<City, City>() {
      @Override public ObservableSource<City> apply(@NonNull Observable<City> upstream) {

        return observable;
      }
    };
  }

  public Observable<City> createCityObservable() {
    return new Observable<City>() {
      @Override protected void subscribeActual(Observer<? super City> observer) {

      }
    };
  }
}
