/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.presentation.internal.di.modules;

import android.content.Context;
import android.content.res.AssetManager;
import com.fernandocejas.android10.sample.data.disk.AssetsReaderImpl;
import com.fernandocejas.android10.sample.data.disk.DiskApi;
import com.fernandocejas.android10.sample.data.disk.DiskApiImpl;
import com.fernandocejas.android10.sample.data.disk.StreamReader;
import com.fernandocejas.android10.sample.data.disk.StreamReaderImpl;
import com.fernandocejas.android10.sample.data.entity.mapper.CityEntityJsonMapper;
import com.fernandocejas.android10.sample.data.executor.JobExecutor;
import com.fernandocejas.android10.sample.data.logic.CitySortingImpl;
import com.fernandocejas.android10.sample.data.logic.TempConverterImpl;
import com.fernandocejas.android10.sample.data.logic.WeatherTempCalcImpl;
import com.fernandocejas.android10.sample.data.net.WeatherRestApi;
import com.fernandocejas.android10.sample.data.net.WeatherRestApiImpl;
import com.fernandocejas.android10.sample.data.net.retrofit.Config;
import com.fernandocejas.android10.sample.data.net.retrofit.WeatherRestApiFactory;
import com.fernandocejas.android10.sample.data.net.retrofit.WeatherRestApiI;
import com.fernandocejas.android10.sample.data.repository.CityDataRepository;
import com.fernandocejas.android10.sample.data.repository.WeatherDataRepository;
import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.interactor.GetCityList;
import com.fernandocejas.android10.sample.domain.logic.CitySorting;
import com.fernandocejas.android10.sample.domain.logic.TempConverter;
import com.fernandocejas.android10.sample.domain.logic.WeatherTempCalc;
import com.fernandocejas.android10.sample.domain.repository.CityRepository;
import com.fernandocejas.android10.sample.domain.repository.WeatherRepository;
import com.fernandocejas.android10.sample.presentation.AndroidApplication;
import com.fernandocejas.android10.sample.presentation.UIThread;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module public class ApplicationModule {
  private static final String CITIES_FILE_NAME = "cities.txt";
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides CitySorting provideCitySorting(CitySortingImpl citySorter) {
    return citySorter;
  }

  @Provides TempConverter provideTempConverter(TempConverterImpl tempConverter) {
    return tempConverter;
  }

  @Provides WeatherTempCalc provideWeatherTempCalc(WeatherTempCalcImpl weatherTempCalc) {
    return weatherTempCalc;
  }

  @Provides GetCityList provideGetCityList(GetCityList getCityList) {
    return getCityList;
  }

  @Provides @Singleton WeatherRepository provideWeatherRepository(
      WeatherDataRepository weatherDataRepository) {
    return weatherDataRepository;
  }

  @Provides @Singleton CityRepository provideCityRepository(CityDataRepository cityDataRepository) {
    return cityDataRepository;
  }

  @Provides @Singleton WeatherRestApiI provideWeatherRestApiI() {
    return new WeatherRestApiFactory(Config.API_BASE_URL).get();
  }

  @Provides @Singleton WeatherRestApi provideWeatherRestApi(WeatherRestApiImpl weatherRestApi) {
    return weatherRestApi;
  }

  @Provides @Singleton DiskApi provideDiskApi(AssetsReaderImpl assetsReader,
      CityEntityJsonMapper cityEntityJsonMapper) {
    return new DiskApiImpl(CITIES_FILE_NAME, assetsReader, cityEntityJsonMapper);
  }

  @Provides @Singleton StreamReader provideStreamReader(StreamReaderImpl streamReader) {
    return streamReader;
  }

  @Provides @Singleton AssetManager provideAssetManager() {
    return application.getAssets();
  }
}
