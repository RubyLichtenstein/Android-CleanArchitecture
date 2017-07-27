package com.fernandocejas.android10.sample.data.entity;

/**
 * Created by Ruby on 7/26/2017.
 */

public class CityEntity {
  private String city;
  private String id;

  public CityEntity(String city, String id) {
    this.city = city;
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public String getId() {
    return id;
  }

  @Override public String toString() {
    return "CityEntity{" + "city='" + city + '\'' + ", id='" + id + '\'' + '}';
  }
}
