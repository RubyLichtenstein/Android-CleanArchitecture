package com.fernandocejas.android10.sample.test.ui.citylist;

import android.content.Context;
import android.support.test.filters.SmallTest;
import com.fernandocejas.android10.sample.domain.interactor.GetCityList;
import com.fernandocejas.android10.sample.presentation.mapper.CityModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.CityModel;
import com.fernandocejas.android10.sample.presentation.navigation.Navigator;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListMvpContract;
import com.fernandocejas.android10.sample.presentation.ui.citylist.CityListPresenter;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by Ruby on 7/28/2017
 */

@SmallTest @RunWith(MockitoJUnitRunner.class) public class CityListPresenterTest {

  public static final String CITY_NAME = "name";
  public static final String CITY_ID = "id";

  @InjectMocks private CityListPresenter cityListPresenter;

  @Mock private Context mockContext;
  @Mock private CityListMvpContract.View mockCityListView;
  @Mock private GetCityList mockGetCityList;
  @Mock private CityModelDataMapper mockCityModelDataMapper;
  @Mock private Navigator mockNavigator;

  public CityListPresenterTest() throws Exception {
    super();
  }

  @Before public void setUp() {

    cityListPresenter.setView(mockCityListView);
    given(mockCityListView.context()).willReturn(mockContext);
    given(mockCityListView.cityClick()).willReturn(Observable.just(createCityModel()));
  }

  @Test @SuppressWarnings("unchecked") public void testCityListPresenterInitialize() {

    cityListPresenter.initialize();

    verify(mockCityListView).showLoading(true);
    verify(mockGetCityList).execute(any(DisposableObserver.class), any());
  }

  @Test public void testOnCityClick() {
    CityModel cityModel = createCityModel();
    cityListPresenter.onCityClick(cityModel);

    verify(mockNavigator).navigateToWeather(mockContext, CITY_ID);
  }

  private CityModel createCityModel() {
    return new CityModel(CITY_NAME, CITY_ID);
  }
}
