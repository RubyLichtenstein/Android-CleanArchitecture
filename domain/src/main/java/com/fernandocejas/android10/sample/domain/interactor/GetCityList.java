package com.fernandocejas.android10.sample.domain.interactor;

import com.fernandocejas.android10.sample.domain.City;
import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.logic.CitySorter;
import com.fernandocejas.android10.sample.domain.logic.CitySorterImpl;
import com.fernandocejas.android10.sample.domain.repository.CityRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/26/2017.
 */

public class GetCityList extends UseCase<List<City>, Void> {

  private final CityRepository cityRepository;
  private final CitySorter citySorter;

  @Inject GetCityList(CityRepository cityRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.cityRepository = cityRepository;
    //todo...
    this.citySorter = new CitySorterImpl();
  }

  @Override Observable<List<City>> buildUseCaseObservable(Void unused) {
    return this.cityRepository.cites().compose(citySorter.applyListSort());
  }
}
