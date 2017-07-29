package com.fernandocejas.android10.sample.domain.interactor;

import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.logic.CitySorting;
import com.fernandocejas.android10.sample.domain.repository.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

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

  //todo!!! add verify mockCitySorter
  @Test public void testGetUserListUseCaseObservableHappyCase() {
    getCityList.buildUseCaseObservable(null);

    verify(mockCityRepository).cites();
    verifyNoMoreInteractions(mockCityRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }
}
