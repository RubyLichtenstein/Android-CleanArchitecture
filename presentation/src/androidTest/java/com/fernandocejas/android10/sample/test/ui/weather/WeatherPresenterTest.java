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
import io.reactivex.observers.DisposableObserver;
import junit.framework.TestCase;
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
 * Created by Ruby on 7/29/2017.
 */
@SmallTest @RunWith(MockitoJUnitRunner.class) public class WeatherPresenterTest extends TestCase {

  private static final String FAKE_CITY_ID = "1234";

  @InjectMocks private WeatherPresenter weatherPresenter;

  @Mock private Context mockContext;
  @Mock private WeatherMvpContract.View mockWeatherView;
  @Mock private GetWeather mockGetWeatherUseCase;
  @Mock private WeatherModelDataMapper mockWeatherModelDataMapper;

  public WeatherPresenterTest() {
    super();
  }

  @Before public void setUp() throws Exception {
    super.setUp();
    weatherPresenter.setView(mockWeatherView);
  }

  @Test @SuppressWarnings("unchecked") public void testPresenterInitialize() {
    given(mockWeatherView.context()).willReturn(mockContext);

    weatherPresenter.initialize(FAKE_CITY_ID);

    verify(mockWeatherView).showLoading(true);
    verify(mockGetWeatherUseCase).execute(any(DisposableObserver.class),
        GetWeather.Params.forCity(FAKE_CITY_ID));
  }

  @Test @SuppressWarnings("unchecked") public void testOnCelsiusClick() {
    WeatherModel weatherModel = createWeatherModel();
    Weather weather = createWeather();

    given(mockWeatherModelDataMapper.transform(weather)).willReturn(weatherModel);

    weatherPresenter.setWeatherModel(weather);
    weatherPresenter.onCelsiusClick();

    verify(weatherPresenter).showWeatherInView(true, weatherModel);
  }

  @Test @SuppressWarnings("unchecked") public void testOnFahrenheitClick() {
    WeatherModel weatherModel = createWeatherModel();
    Weather weather = createWeather();

    given(mockWeatherModelDataMapper.transform(weather)).willReturn(weatherModel);

    weatherPresenter.setWeatherModel(weather);
    weatherPresenter.onCelsiusClick();

    verify(weatherPresenter).showWeatherInView(false, weatherModel);
  }

  @NonNull private Weather createWeather() {
    return new Weather();
  }

  @NonNull private WeatherModel createWeatherModel() {
    return new WeatherModel();
  }
}
