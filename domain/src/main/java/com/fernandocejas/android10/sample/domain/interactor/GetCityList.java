package com.fernandocejas.android10.sample.domain.interactor;

import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.logic.CitySorting;
import com.fernandocejas.android10.sample.domain.repository.CityRepository;
import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/26/2017
 */

public class GetCityList extends UseCase<City, Void> {

  private final CityRepository cityRepository;
  private final CitySorting citySorting;

  @Inject GetCityList(CityRepository cityRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread, CitySorting citySorting) {
    super(threadExecutor, postExecutionThread);
    Preconditions.checkNotNull(cityRepository);
    Preconditions.checkNotNull(threadExecutor);
    Preconditions.checkNotNull(postExecutionThread);
    this.citySorting = citySorting;
    this.cityRepository = cityRepository;
  }

  @Override Observable<City> buildUseCaseObservable(Void unused) {
    return this.cityRepository.cites().compose(citySorting.apply());
  }
}
