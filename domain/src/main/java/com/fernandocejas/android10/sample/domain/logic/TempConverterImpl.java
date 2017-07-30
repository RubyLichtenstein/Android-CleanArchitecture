package com.fernandocejas.android10.sample.domain.logic;

import java.text.DecimalFormat;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ruby on 7/28/2017.
 */
@Singleton public class TempConverterImpl implements TempConverter {
  private static final float F32 = 32f;
  private static final float F5 = 5f;
  private static final float F9 = 9f;
  private DecimalFormat twoDecimalPlaceFormat;

  @Inject public TempConverterImpl() {
    twoDecimalPlaceFormat = new DecimalFormat("#.##");
  }

  @Override public float toCelsius(float fahrenheit) {
    float celsius = ((F5 / F9) * (fahrenheit - F32));
    return format(celsius, twoDecimalPlaceFormat);
  }

  @Override public float toFahrenheit(float celsius) {
    float fahrenheit = (F9 / F5) * celsius + F32;
    return format(fahrenheit, twoDecimalPlaceFormat);
  }

  private float format(float in, DecimalFormat decimalFormat) {
    return Float.valueOf(decimalFormat.format(in));
  }
}
