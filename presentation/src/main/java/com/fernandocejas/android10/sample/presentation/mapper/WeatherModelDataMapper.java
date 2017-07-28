package com.fernandocejas.android10.sample.presentation.mapper;

import com.fernandocejas.android10.sample.domain.Weather;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.model.WeatherModel;
import javax.inject.Inject;

/**
 * Created by Ruby on 7/28/2017.
 */
@PerActivity public class WeatherModelDataMapper {

  @Inject public WeatherModelDataMapper() {
  }

  public WeatherModel transform(Weather weather) {
    //todo...
    //if (user == null) {
    //  throw new IllegalArgumentException("Cannot transform a null value");
    //}
    //final UserModel userModel = new UserModel(user.getUserId());
    //userModel.setCoverUrl(user.getCoverUrl());
    //userModel.setFullName(user.getFullName());
    //userModel.setEmail(user.getEmail());
    //userModel.setDescription(user.getDescription());
    //userModel.setFollowers(user.getFollowers());

    return new WeatherModel();
  }
}
