package com.fernandocejas.android10.sample.domain.logic;

import com.fernandocejas.android10.sample.domain.logic.TempConverter;
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
  private DecimalFormat oneDecimalPlaceFormat;

  @Inject public TempConverterImpl() {
    oneDecimalPlaceFormat = new DecimalFormat("#.#");
  }

  @Override public float toCelsius(float fahrenheit) {
    float celsius = ((F5 / F9) * (fahrenheit - F32));
    return format(celsius, oneDecimalPlaceFormat);
  }

  @Override public float toFahrenheit(float celsius) {
    float fahrenheit = (F9 / F5) * celsius + F32;
    return format(fahrenheit, oneDecimalPlaceFormat);
  }

  private float format(float in, DecimalFormat decimalFormat) {
    return Float.valueOf(decimalFormat.format(in));
  }
}
