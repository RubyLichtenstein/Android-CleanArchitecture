package com.fernandocejas.android10.sample.domain.interactor;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.logic.TempConverterImpl;
import com.fernandocejas.android10.sample.domain.logic.WeatherTempCalc;
import com.fernandocejas.android10.sample.domain.logic.WeatherTempCalcImpl;
import com.fernandocejas.android10.sample.domain.repository.WeatherRepository;
import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/26/2017.
 */

public class GetWeather extends UseCase<Weather, GetWeather.Params> {

  private final WeatherRepository weatherRepository;
  private final WeatherTempCalc weatherTempCalc;

  @Inject GetWeather(WeatherRepository weatherRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    Preconditions.checkNotNull(weatherRepository);
    Preconditions.checkNotNull(threadExecutor);
    Preconditions.checkNotNull(postExecutionThread);
    this.weatherRepository = weatherRepository;
    this.weatherTempCalc = new WeatherTempCalcImpl(new TempConverterImpl());
  }

  @Override Observable<Weather> buildUseCaseObservable(Params params) {
    Preconditions.checkNotNull(params);
    return weatherRepository.weather(params.cityId).compose(weatherTempCalc.apply());
  }

  public static final class Params {

    private final String cityId;

    private Params(String cityId) {
      this.cityId = cityId;
    }

    public static Params forCity(String cityId) {
      return new Params(cityId);
    }
  }
}
