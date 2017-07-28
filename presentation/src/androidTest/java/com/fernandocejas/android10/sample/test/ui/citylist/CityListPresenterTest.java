package com.fernandocejas.android10.sample.test.ui.citylist;

import android.content.Context;
import com.fernandocejas.android10.sample.domain.interactor.GetCityList;
import com.fernandocejas.android10.sample.presentation.mapper.CityModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListPresenter;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Ruby on 7/28/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class CityListPresenterTest {

  @InjectMocks
  private CityListPresenter cityListPresenter;

  @Mock private Context mockContext;
  @Mock private CityListView mockCityListView;
  @Mock private GetCityList mockGetCityList;
  @Mock private CityModelDataMapper mockCityModelDataMapper;

  @Before
  public void setUp() {
    cityListPresenter.setView(mockCityListView);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testUserListPresenterInitialize() {
    given(mockCityListView.context()).willReturn(mockContext);
    given(mockCityListView.getCityClickObs()).willReturn(new Observable<CityModel>() {
      @Override protected void subscribeActual(Observer<? super CityModel> observer) {

      }
    });

    cityListPresenter.initialize();


    verify(mockCityListView).hideRetry();
    verify(mockCityListView).showLoading();
    verify(mockCityListView).getCityClickObs();
    verify(mockGetCityList).execute(any(DisposableObserver.class), any(Void.class));
  }

  //todo more test cases!!!
}
