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
package com.fernandocejas.android10.sample.data.net.retrofit;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * RestApi for retrieving data from the network.
 */
public interface WeatherRestApiI {
  /**
   * Retrieves an {@link Observable} which will emit a {@link WeatherEntity}.
   *
   * @param cityId The city id used to get weather data.
   */
  @GET(Config.GET_WEATHER_PATH)
  Observable<WeatherEntity> WeatherEntityByCityId(final int cityId);
}
