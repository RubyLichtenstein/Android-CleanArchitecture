package com.fernandocejas.android10.sample.test.ui.weather;

import android.content.Context;
import com.fernandocejas.android10.sample.domain.interactor.GetWeather;
import com.fernandocejas.android10.sample.presentation.mapper.WeatherModelDataMapper;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherPresenter;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherView;
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
 * Created by Ruby on 7/29/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class WeatherPresenterTest {

  private static final String FAKE_CITY_ID = "1234";

  @InjectMocks private WeatherPresenter weatherPresenter;

  @Mock private Context mockContext;
  @Mock private WeatherView mockWeatherView;
  @Mock private GetWeather mockGetWeatherUseCase;
  @Mock private WeatherModelDataMapper mockWeatherModelDataMapper;

  @Before public void setUp() {
    weatherPresenter.setView(mockWeatherView);
  }

  @Test @SuppressWarnings("unchecked") public void testPresenterInitialize() {
    given(mockWeatherView.context()).willReturn(mockContext);

    weatherPresenter.initialize(FAKE_CITY_ID);

    verify(mockWeatherView).showLoading(true);
    verify(mockGetWeatherUseCase).execute(any(DisposableObserver.class),
        GetWeather.Params.forCity(FAKE_CITY_ID));
  }
}
