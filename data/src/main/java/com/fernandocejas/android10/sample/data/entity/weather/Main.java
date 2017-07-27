package com.fernandocejas.android10.sample.data.entity.weather;

public class Main {
  public Float temp;
  public Integer pressure;
  public Integer humidity;
  public Integer tempMin;
  public Integer tempMax;

  public Float getTemp() {

    return temp;
  }

  public void setTemp(Float temp) {
    this.temp = temp;
  }

  public Integer getPressure() {
    return pressure;
  }

  public void setPressure(Integer pressure) {
    this.pressure = pressure;
  }

  public Integer getHumidity() {
    return humidity;
  }

  public void setHumidity(Integer humidity) {
    this.humidity = humidity;
  }

  public Integer getTempMin() {
    return tempMin;
  }

  public void setTempMin(Integer tempMin) {
    this.tempMin = tempMin;
  }

  public Integer getTempMax() {
    return tempMax;
  }

  public void setTempMax(Integer tempMax) {
    this.tempMax = tempMax;
  }
}
