package com.fernandocejas.android10.sample.domain;

/**
 * Created by Ruby on 7/26/2017.
 */

public class City {
  String name;
  String id;

  public City(String name, String id) {
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
