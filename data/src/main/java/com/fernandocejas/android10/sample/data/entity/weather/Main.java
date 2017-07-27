package com.fernandocejas.android10.sample.data.entity.weather;

import com.google.gson.annotations.SerializedName;

public class Main {
  public Float temp;
  public Float pressure;
  public Float humidity;
  @SerializedName("temp_min") public Float tempMin;
  @SerializedName("temp_max") public Float tempMax;

  public Float getTemp() {
    return temp;
  }

  public void setTemp(Float temp) {
    this.temp = temp;
  }

  public Float getPressure() {
    return pressure;
  }

  public void setPressure(Float pressure) {
    this.pressure = pressure;
  }

  public Float getHumidity() {
    return humidity;
  }

  public void setHumidity(Float humidity) {
    this.humidity = humidity;
  }

  public Float getTempMin() {
    return tempMin;
  }

  public void setTempMin(Float tempMin) {
    this.tempMin = tempMin;
  }

  public Float getTempMax() {
    return tempMax;
  }

  public void setTempMax(Float tempMax) {
    this.tempMax = tempMax;
  }
}
