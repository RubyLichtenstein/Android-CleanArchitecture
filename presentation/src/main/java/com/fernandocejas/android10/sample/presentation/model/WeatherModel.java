package com.fernandocejas.android10.sample.presentation.model;

/**
 * Created by Ruby on 7/28/2017.
 */

public class WeatherModel {
  private String cityName;
  private String description;
  private String iconUrl;
  private String currentTempCelsius;
  private String currentTempFahrenheit;
  private String todayTempRangeCelsius;
  private String todayTempRangeFahrenheit;

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }

  public String getCurrentTempCelsius() {
    return currentTempCelsius;
  }

  public void setCurrentTempCelsius(String currentTempCelsius) {
    this.currentTempCelsius = currentTempCelsius;
  }

  public String getCurrentTempFahrenheit() {
    return currentTempFahrenheit;
  }

  public void setCurrentTempFahrenheit(String currentTempFahrenheit) {
    this.currentTempFahrenheit = currentTempFahrenheit;
  }

  public String getTodayTempRangeCelsius() {
    return todayTempRangeCelsius;
  }

  public void setTodayTempRangeCelsius(String todayTempRangeCelsius) {
    this.todayTempRangeCelsius = todayTempRangeCelsius;
  }

  public String getTodayTempRangeFahrenheit() {
    return todayTempRangeFahrenheit;
  }

  public void setTodayTempRangeFahrenheit(String todayTempRangeFahrenheit) {
    this.todayTempRangeFahrenheit = todayTempRangeFahrenheit;
  }
}
