package com.fernandocejas.android10.sample.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ruby on 7/26/2017.
 */

public class CityTest {
  private static final String FAKE_CITY_NAME = "London";
  private static final String FAKE_CITY_ID = "1234";

  private City city;

  @Before public void setUp() {
    city = new City(FAKE_CITY_NAME, FAKE_CITY_ID);
  }

  @Test public void testUserConstructorHappyCase() {
    final String cityName = city.getName();
    final String cityId = city.getId();

    assertThat(cityName).isEqualTo(FAKE_CITY_NAME);
    assertThat(cityId).isEqualTo(FAKE_CITY_ID);
  }
}
