package com.fernandocejas.android10.sample.presentation.model;

/**
 * Created by Ruby on 7/28/2017.
 */

public class CityModel {
  String name;
  String id;

  public CityModel(String name, String id) {
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
