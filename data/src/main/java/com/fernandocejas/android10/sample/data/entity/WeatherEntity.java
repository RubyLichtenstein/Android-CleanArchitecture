package com.fernandocejas.android10.sample.data.entity;

import com.fernandocejas.android10.sample.data.entity.weather.Clouds;
import com.fernandocejas.android10.sample.data.entity.weather.Coord;
import com.fernandocejas.android10.sample.data.entity.weather.Main;
import com.fernandocejas.android10.sample.data.entity.weather.Sys;
import com.fernandocejas.android10.sample.data.entity.weather.WeatherEntityInernal;
import com.fernandocejas.android10.sample.data.entity.weather.Wind;
import java.util.List;

/**
 * Created by Ruby on 7/26/2017.
 */

public class WeatherEntity {
  public Coord coord;
  private List<WeatherEntityInernal> weather = null;
  private String base;
  private Main main;
  public float visibility;
  public Wind wind;
  public Clouds clouds;
  public float dt;
  public Sys sys;
  private Integer id;
  private String name;
  public Integer cod;

  public List<WeatherEntityInernal> getWeather() {
    return weather;
  }

  public void setWeather(List<WeatherEntityInernal> weather) {
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
}
