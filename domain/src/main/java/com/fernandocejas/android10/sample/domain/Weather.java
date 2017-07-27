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
  private float temp;
  private float tempMin;
  private float tempMax;

  //not include here only in presentation layer
  //private float tempFahrenheit;
  //private float tempMinFahrenheit;
  //private float tempMaxFahrenheit;

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

  public float getTemp() {
    return temp;
  }

  public void setTemp(float temp) {
    this.temp = temp;
  }

  public float getTempMin() {
    return tempMin;
  }

  public void setTempMin(float tempMin) {
    this.tempMin = tempMin;
  }

  public float getTempMax() {
    return tempMax;
  }

  public void setTempMax(float tempMax) {
    this.tempMax = tempMax;
  }
}
