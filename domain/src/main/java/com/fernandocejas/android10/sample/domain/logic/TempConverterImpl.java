package com.fernandocejas.android10.sample.domain.logic;

import java.text.DecimalFormat;

/**
 * Created by Ruby on 7/28/2017.
 */

public class TempConverterImpl implements TempConverter {
  DecimalFormat oneDecimalPlaceFormat;

  public TempConverterImpl() {
    oneDecimalPlaceFormat = new DecimalFormat("#.#");
  }

  @Override public float toCelsius(float fahrenheit) {
    float celsius = ((5f / 9f) * (fahrenheit - 32f));
    return format(celsius, oneDecimalPlaceFormat);
  }

  @Override public float toFahrenheit(float celsius) {
    float fahrenheit = (9f / 5f) * celsius + 32f;
    return format(fahrenheit, oneDecimalPlaceFormat);
  }

  private float format(float in, DecimalFormat decimalFormat) {
    return Float.valueOf(decimalFormat.format(in));
  }
}
