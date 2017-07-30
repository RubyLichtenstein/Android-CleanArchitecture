package com.fernandocejas.android10.sample.test.ui.citylist;

import android.content.Context;
import com.fernandocejas.android10.sample.domain.interactor.GetCityList;
import com.fernandocejas.android10.sample.presentation.mapper.CityModelDataMapper;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListMvpContract;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListPresenter;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by Ruby on 7/28/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class CityListPresenterTest extends TestCase {

  public static final String NAME = "name";
  public static final String ID = "id";
  @InjectMocks private CityListPresenter cityListPresenter;

  @Mock private Context mockContext;
  @Mock private CityListMvpContract.View mockCityListView;
  @Mock private GetCityList mockGetCityList;
  @Mock private CityModelDataMapper mockCityModelDataMapper;

  public CityListPresenterTest() {

  }

  @Before public void setUp() {
    cityListPresenter.setView(mockCityListView);
  }

  @Test @SuppressWarnings("unchecked") public void testUserListPresenterInitialize() {
    //given(mockCityListView.context()).willReturn(mockContext);
    //given(mockCityListView.getCityClickObs()).willReturn(Observable.just(new CityModel(NAME, ID)));
    //
    //cityListPresenter.initialize();
    //
    //verify(mockCityListView).showLoading(true);
    //verify(mockCityListView).getCityClickObs();
    //verify(mockGetCityList).execute(any(DisposableObserver.class), any(Void.class));
  }

  //todo more test cases!!!
}
