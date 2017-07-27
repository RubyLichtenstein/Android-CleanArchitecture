package com.fernandocejas.android10.sample.data.entity;

import com.fernandocejas.android10.sample.data.entity.weather.Clouds;
import com.fernandocejas.android10.sample.data.entity.weather.Coord;
import com.fernandocejas.android10.sample.data.entity.weather.Main;
import com.fernandocejas.android10.sample.data.entity.weather.Sys;
import com.fernandocejas.android10.sample.data.entity.weather.Weather;
import com.fernandocejas.android10.sample.data.entity.weather.Wind;
import java.util.List;

/**
 * Created by Ruby on 7/26/2017.
 */

public class WeatherEntity {
  public Coord coord;
  public List<Weather> weather = null;
  public String base;
  public Main main;
  public Integer visibility;
  public Wind wind;
  public Clouds clouds;
  public Integer dt;
  public Sys sys;
  public Integer id;
  public String name;
  public Integer cod;

  public Coord getCoord() {
    return coord;
  }

  public void setCoord(Coord coord) {
    this.coord = coord;
  }

  public List<Weather> getWeather() {
    return weather;
  }

  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public Main getMain() {
    return main;
  }

  public void setMain(Main main) {
    this.main = main;
  }

  public Integer getVisibility() {
    return visibility;
  }

  public void setVisibility(Integer visibility) {
    this.visibility = visibility;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }

  public Clouds getClouds() {
    return clouds;
  }

  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  public Integer getDt() {
    return dt;
  }

  public void setDt(Integer dt) {
    this.dt = dt;
  }

  public Sys getSys() {
    return sys;
  }

  public void setSys(Sys sys) {
    this.sys = sys;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCod() {
    return cod;
  }

  public void setCod(Integer cod) {
    this.cod = cod;
  }
}
