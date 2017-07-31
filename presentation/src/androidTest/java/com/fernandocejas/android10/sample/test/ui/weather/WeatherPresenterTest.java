package com.fernandocejas.android10.sample.test.ui.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.filters.SmallTest;
import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.interactor.GetWeather;
import com.fernandocejas.android10.sample.presentation.mapper.WeatherModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherMvpContract;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherPresenter;
import com.fernandocejas.android10.sample.presentation.ui.weather.WeatherViewModel;
import io.reactivex.observers.DisposableObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Ruby on 7/29/2017
 */
@SmallTest @RunWith(MockitoJUnitRunner.class) public class WeatherPresenterTest {

  private static final String FAKE_CITY_ID = "1234";

  @InjectMocks private WeatherPresenter weatherPresenter;

  @Mock private Context mockContext;
  @Mock private WeatherMvpContract.View mockWeatherView;
  @Mock private GetWeather mockGetWeatherUseCase;
  @Mock private WeatherModelDataMapper mockWeatherModelDataMapper;

  public WeatherPresenterTest() {
  }

  @Before public void setUp() throws Exception {
    weatherPresenter.setView(mockWeatherView);
  }

  @Test @SuppressWarnings("unchecked") public void testPresenterInitialize() {
    given(mockWeatherView.context()).willReturn(mockContext);

    weatherPresenter.initialize(FAKE_CITY_ID);

    verify(mockWeatherView).showLoading(true);
    verify(mockGetWeatherUseCase).execute(any(DisposableObserver.class), any());
  }

  @Test public void testOnCelsiusClick() {
    //setup
    boolean celsius = true;
    WeatherModel weatherModel = createWeatherModel();
    Weather weather = createWeather();
    WeatherViewModel weatherViewModel = createWeatherViewModel();
    given(mockWeatherModelDataMapper.transform(weather)).willReturn(weatherModel);
    given(mockWeatherModelDataMapper.transform(celsius, weatherModel)).willReturn(weatherViewModel);

    //run
    weatherPresenter.setWeatherModel(weather);
    weatherPresenter.onCelsiusClick();

    //assert
    verify(mockWeatherModelDataMapper).transform(celsius, weatherModel);
    verify(mockWeatherView).renderWeather(weatherViewModel);
  }

  @Test public void testOnFahrenheitClick() {
    //setup
    boolean celsius = false;
    WeatherModel weatherModel = createWeatherModel();
    Weather weather = createWeather();
    WeatherViewModel weatherViewModel = createWeatherViewModel();
    given(mockWeatherModelDataMapper.transform(weather)).willReturn(weatherModel);
    given(mockWeatherModelDataMapper.transform(celsius, weatherModel)).willReturn(weatherViewModel);

    //run
    weatherPresenter.setWeatherModel(weather);
    weatherPresenter.onFahrenheitClick();

    //assert
    verify(mockWeatherModelDataMapper).transform(celsius, weatherModel);
    verify(mockWeatherView).renderWeather(weatherViewModel);
  }

  @NonNull private WeatherViewModel createWeatherViewModel() {
    return new WeatherViewModel();
  }

  @NonNull private Weather createWeather() {
    return new Weather();
  }

  @NonNull private WeatherModel createWeatherModel() {
    return new WeatherModel();
  }
}
