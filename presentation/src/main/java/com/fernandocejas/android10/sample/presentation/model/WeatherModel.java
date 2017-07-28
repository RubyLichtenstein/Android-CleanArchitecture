package com.fernandocejas.android10.sample.presentation.model;

/**
 * Created by Ruby on 7/28/2017.
 */

public class WeatherModel {
  private String cityName;
  private String main;
  private String description;
  private String icon;
  private float tempCelsius;
  private float tempMinCelsius;
  private float tempMaxCelsius;
  private float tempFahrenheit;
  private float tempMinFahrenheit;
  private float tempMaxFahrenheit;

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
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

  public float getTempMinCelsius() {
    return tempMinCelsius;
  }

  public void setTempMinCelsius(float tempMinCelsius) {
    this.tempMinCelsius = tempMinCelsius;
  }

  public float getTempMaxCelsius() {
    return tempMaxCelsius;
  }

  public void setTempMaxCelsius(float tempMaxCelsius) {
    this.tempMaxCelsius = tempMaxCelsius;
  }

  public float getTempFahrenheit() {
    return tempFahrenheit;
  }

  public void setTempFahrenheit(float tempFahrenheit) {
    this.tempFahrenheit = tempFahrenheit;
  }

  public float getTempMinFahrenheit() {
    return tempMinFahrenheit;
  }

  public void setTempMinFahrenheit(float tempMinFahrenheit) {
    this.tempMinFahrenheit = tempMinFahrenheit;
  }

  public float getTempMaxFahrenheit() {
    return tempMaxFahrenheit;
  }

  public void setTempMaxFahrenheit(float tempMaxFahrenheit) {
    this.tempMaxFahrenheit = tempMaxFahrenheit;
  }

  @Override public String toString() {
    return "WeatherModel{"
        + "cityName='"
        + cityName
        + '\''
        + ", main='"
        + main
        + '\''
        + ", description='"
        + description
        + '\''
        + ", icon='"
        + icon
        + '\''
        + ", tempCelsius="
        + tempCelsius
        + ", tempMinCelsius="
        + tempMinCelsius
        + ", tempMaxCelsius="
        + tempMaxCelsius
        + ", tempFahrenheit="
        + tempFahrenheit
        + ", tempMinFahrenheit="
        + tempMinFahrenheit
        + ", tempMaxFahrenheit="
        + tempMaxFahrenheit
        + '}';
  }
}
