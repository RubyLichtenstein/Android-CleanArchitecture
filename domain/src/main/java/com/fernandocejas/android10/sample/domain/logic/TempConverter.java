package com.fernandocejas.android10.sample.domain.logic;

/**
 * Created by Ruby on 7/28/2017.
 */

public interface TempConverter {
  float toCelsius(float fahrenheit);

  float toFahrenheit(float Celsius);
}
