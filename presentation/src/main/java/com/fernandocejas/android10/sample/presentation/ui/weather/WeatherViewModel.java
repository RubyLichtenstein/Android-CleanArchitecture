package com.fernandocejas.android10.sample.presentation.ui.weather;

/**
 * Created by Ruby on 7/28/2017.
 */

public class WeatherViewModel {
  private String cityName;
  private String description;
  private String iconUrl;
  private String currentTemp;
  private String todayTempRange;

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

  public String getCurrentTemp() {
    return currentTemp;
  }

  public void setCurrentTemp(String currentTemp) {
    this.currentTemp = currentTemp;
  }

  public String getTodayTempRange() {
    return todayTempRange;
  }

  public void setTodayTempRange(String todayTempRange) {
    this.todayTempRange = todayTempRange;
  }
}
