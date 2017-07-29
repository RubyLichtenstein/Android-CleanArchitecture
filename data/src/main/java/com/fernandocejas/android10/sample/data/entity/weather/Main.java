package com.fernandocejas.android10.sample.data.entity.weather;

import com.google.gson.annotations.SerializedName;

public class Main {
  private float temp;
  private float pressure;
  private float humidity;
  @SerializedName("temp_min") private float tempMin;
  @SerializedName("temp_max") private float tempMax;

  public float getTemp() {
    return temp;
  }

  public void setTemp(float temp) {
    this.temp = temp;
  }

  public float getPressure() {
    return pressure;
  }

  public void setPressure(float pressure) {
    this.pressure = pressure;
  }

  public float getHumidity() {
    return humidity;
  }

  public void setHumidity(float humidity) {
    this.humidity = humidity;
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
