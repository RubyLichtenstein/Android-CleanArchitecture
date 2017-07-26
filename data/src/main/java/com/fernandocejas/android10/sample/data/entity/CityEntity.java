package com.fernandocejas.android10.sample.data.entity;

/**
 * Created by Ruby on 7/26/2017.
 */

public class CityEntity {
  private String name;
  private String id;

  public CityEntity(String name, String id) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }
}
