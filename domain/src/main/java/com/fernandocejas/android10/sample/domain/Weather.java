package com.fernandocejas.android10.sample.domain;

/**
 * Created by Ruby on 7/26/2017.
 */

public class Weather {
  /*city name*/
  private String name;
  private String main;
  private String description;
  private String icon;
  private float tempCelsius;
  private float tempCelsiusMin;
  private float tempCelsiusMax;
  private float tempFahrenheit;
  private float tempFahrenheitMin;
  private float tempFahrenheitMax;

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

  public float getTempCelsiusMin() {
    return tempCelsiusMin;
  }

  public void setTempCelsiusMin(float tempCelsiusMin) {
    this.tempCelsiusMin = tempCelsiusMin;
  }

  public float getTempCelsiusMax() {
    return tempCelsiusMax;
  }

  public void setTempCelsiusMax(float tempCelsiusMax) {
    this.tempCelsiusMax = tempCelsiusMax;
  }

  public float getTempFahrenheit() {
    return tempFahrenheit;
  }

  public void setTempFahrenheit(float tempFahrenheit) {
    this.tempFahrenheit = tempFahrenheit;
  }

  public float getTempFahrenheitMin() {
    return tempFahrenheitMin;
  }

  public void setTempFahrenheitMin(float tempFahrenheitMin) {
    this.tempFahrenheitMin = tempFahrenheitMin;
  }

  public float getTempFahrenheitMax() {
    return tempFahrenheitMax;
  }

  public void setTempFahrenheitMax(float tempFahrenheitMax) {
    this.tempFahrenheitMax = tempFahrenheitMax;
  }
}
