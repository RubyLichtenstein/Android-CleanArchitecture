package com.fernandocejas.android10.sample.domain;

/**
 * Created by Ruby on 7/26/2017.
 */

/**
 * Weather object need for domain from presenter
 */
public class WeatherIn {
  private String name;
  private String main;
  private String description;
  private String icon;
  private float tempCelsius;
  private float tempCelsiusLow;
  private float tempCelsiusHigh;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMain() {
    return main;
  }

  public void setMain(String main) {
    this.main = main;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public float getTempCelsius() {
    return tempCelsius;
  }

  public void setTempCelsius(float tempCelsius) {
    this.tempCelsius = tempCelsius;
  }

  public float getTempCelsiusLow() {
    return tempCelsiusLow;
  }

  public void setTempCelsiusLow(float tempCelsiusLow) {
    this.tempCelsiusLow = tempCelsiusLow;
  }

  public float getTempCelsiusHigh() {
    return tempCelsiusHigh;
  }

  public void setTempCelsiusHigh(float tempCelsiusHigh) {
    this.tempCelsiusHigh = tempCelsiusHigh;
  }
}
