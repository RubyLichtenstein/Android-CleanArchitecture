package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.City;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ruby on 7/28/2017.
 */

public class CitySortingTest {

  private static final String LONDON = "London";
  private static final String PARIS = "Paris";
  private static final String BERLIN = "Berlin";
  private static final String TEL_AVIV = "Tel Aviv";

  private static final String ID = "1234";

  private CitySorting citySorting;

  @Before public void setUp() {
    citySorting = new CitySortingImpl();
  }

  @Test public void testApplySort() {
    List<City> cities = createCityList();

    TestObserver<City> testObserver = new TestObserver<>();
    Observable.fromIterable(cities).compose(citySorting.apply()).subscribe(testObserver);

    testObserver.assertComplete();
    assertThat(testObserver.values().get(0).getName()).isEqualTo(BERLIN);
    assertThat(testObserver.values().get(1).getName()).isEqualTo(LONDON);
    assertThat(testObserver.values().get(2).getName()).isEqualTo(PARIS);
    assertThat(testObserver.values().get(3).getName()).isEqualTo(TEL_AVIV);
  }

  @Test public void testApplyListSort() {
    List<City> cities = createCityList();

    TestObserver<List<City>> testObserver = new TestObserver<>();
    Observable.just(cities).compose(citySorting.applyList()).subscribe(testObserver);

    testObserver.assertComplete();

    List<City> editedList = testObserver.values().get(0);
    assertThat(editedList.get(0).getName()).isEqualTo(BERLIN);
    assertThat(editedList.get(1).getName()).isEqualTo(LONDON);
    assertThat(editedList.get(2).getName()).isEqualTo(PARIS);
    assertThat(editedList.get(3).getName()).isEqualTo(TEL_AVIV);
  }

  private List<City> createCityList() {
    return new ArrayList<City>() {{
      add(new City(LONDON, ID));
      add(new City(PARIS, ID));
      add(new City(BERLIN, ID));
      add(new City(TEL_AVIV, ID));
    }};
  }
}
