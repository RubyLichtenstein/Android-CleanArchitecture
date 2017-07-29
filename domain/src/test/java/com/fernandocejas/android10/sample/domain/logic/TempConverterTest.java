package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.logic.TempConverter;
import com.fernandocejas.android10.sample.domain.logic.TempConverterImpl;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ruby on 7/28/2017.
 */

public class TempConverterTest {
  private TempConverter tempConverter;
  private float[] tempCelsius;
  private float[] tempFahrenheit;

  @Before public void setUp() {
    tempConverter = new TempConverterImpl();
    tempCelsius = new float[] { 0f, 1f, 2f, 3f };
    tempFahrenheit = new float[] { 32.0f, 33.8f, 35.6f, 37.4f };
  }

  @Test public void testFahrenheitToCelsius() {
    for (int i = 0; i < tempCelsius.length; i++) {
      assertThat(tempCelsius[i]).isEqualTo(tempConverter.toCelsius(tempFahrenheit[i]));
    }
  }

  @Test public void testCelsiusToFahrenheit() {
    for (int i = 0; i < tempCelsius.length; i++) {
      assertThat(tempFahrenheit[i]).isEqualTo(tempConverter.toFahrenheit(tempCelsius[i]));
    }
  }
}
