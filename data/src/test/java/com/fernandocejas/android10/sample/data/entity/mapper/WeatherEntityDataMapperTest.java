package com.fernandocejas.android10.sample.data.entity.mapper;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import com.fernandocejas.android10.sample.domain.WeatherRaw;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by Ruby on 7/27/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class WeatherEntityDataMapperTest {
  private static final String WEATHER_JSON_1 = "{\n"
      + "  \"coord\": {\n"
      + "    \"lon\": -0.13,\n"
      + "    \"lat\": 51.51\n"
      + "  },\n"
      + "  \"weather\": [\n"
      + "    {\n"
      + "      \"id\": 500,\n"
      + "      \"main\": \"Rain\",\n"
      + "      \"description\": \"light rain\",\n"
      + "      \"icon\": \"10d\"\n"
      + "    },\n"
      + "    {\n"
      + "      \"id\": 701,\n"
      + "      \"main\": \"Mist\",\n"
      + "      \"description\": \"mist\",\n"
      + "      \"icon\": \"50d\"\n"
      + "    },\n"
      + "    {\n"
      + "      \"id\": 721,\n"
      + "      \"main\": \"Haze\",\n"
      + "      \"description\": \"haze\",\n"
      + "      \"icon\": \"50d\"\n"
      + "    }\n"
      + "  ],\n"
      + "  \"base\": \"stations\",\n"
      + "  \"main\": {\n"
      + "    \"temp\": 19,\n"
      + "    \"pressure\": 1004,\n"
      + "    \"humidity\": 82,\n"
      + "    \"temp_min\": 18,\n"
      + "    \"temp_max\": 20\n"
      + "  },\n"
      + "  \"visibility\": 10000,\n"
      + "  \"wind\": {\n"
      + "    \"speed\": 6.7,\n"
      + "    \"deg\": 230,\n"
      + "    \"gust\": 11.8\n"
      + "  },\n"
      + "  \"clouds\": {\n"
      + "    \"all\": 90\n"
      + "  },\n"
      + "  \"dt\": 1501087800,\n"
      + "  \"sys\": {\n"
      + "    \"type\": 1,\n"
      + "    \"id\": 5091,\n"
      + "    \"message\": 0.0081,\n"
      + "    \"country\": \"GB\",\n"
      + "    \"sunrise\": 1501042595,\n"
      + "    \"sunset\": 1501098993\n"
      + "  },\n"
      + "  \"id\": 2643743,\n"
      + "  \"name\": \"London\",\n"
      + "  \"cod\": 200\n"
      + "}";

  private static final String WEATHER_JSON_2 = "{\"coord\":\n"
      + "{\"lon\":145.77,\"lat\":-16.92},\n"
      + "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\n"
      + "\"base\":\"cmc stations\",\n"
      + "\"main\":{\"temp\":293.25,\"pressure\":1019,\"humidity\":83,\"temp_min\":289.82,\"temp_max\":295.37},\n"
      + "\"wind\":{\"speed\":5.1,\"deg\":150},\n"
      + "\"clouds\":{\"all\":75},\n"
      + "\"rain\":{\"3h\":3},\n"
      + "\"dt\":1435658272,\n"
      + "\"sys\":{\"type\":1,\"id\":8166,\"message\":0.0166,\"country\":\"AU\",\"sunrise\":1435610796,\"sunset\":1435650870},\n"
      + "\"id\":2172797,\n"
      + "\"name\":\"Cairns\",\n"
      + "\"cod\":200}";

  private static final String WEATHER_JSON_3 =
      "{\"coord\":{\"lon\":145.77,\"lat\":-16.92},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"base\":\"stations\",\"main\":{\"temp\":20,\"pressure\":1018,\"humidity\":77,\"temp_min\":20,\"temp_max\":20},\"visibility\":10000,\"wind\":{\"speed\":3.6,\"deg\":160},\"clouds\":{\"all\":20},\"dt\":1501340400,\"sys\":{\"type\":1,\"id\":8166,\"message\":0.0037,\"country\":\"AU\",\"sunrise\":1501274594,\"sunset\":1501315421},\"id\":2172797,\"name\":\"Cairns\",\"cod\":200}";

  private WeatherEntityDataMapper weatherEntityDataMapper;

  @Before public void setUp() {
    weatherEntityDataMapper = new WeatherEntityDataMapper();
  }

  @Test public void testTransformWeatherEntity() {
    assertTransformWeatherEntity(WEATHER_JSON_1);
    assertTransformWeatherEntity(WEATHER_JSON_2);
    assertTransformWeatherEntity(WEATHER_JSON_3);
  }

  public void assertTransformWeatherEntity(final String weatherJson) {
    WeatherEntity weatherEntity = createWeatherEntity(weatherJson);
    WeatherRaw weather = weatherEntityDataMapper.transform(weatherEntity);

    assertThat(weather, is(instanceOf(WeatherRaw.class)));
    assertThat(weather.getMain(), is(weatherEntity.getWeather().get(0).getMain()));
    assertThat(weather.getDescription(), is(weatherEntity.getWeather().get(0).getDescription()));
    assertThat(weather.getIcon(), is(weatherEntity.getWeather().get(0).getIcon()));
    assertThat(weather.getName(), is(weatherEntity.getName()));
    assertThat(weather.getTempCelsius(), is(weatherEntity.getMain().getTemp()));
    assertThat(weather.getTempCelsiusHigh(), is(weatherEntity.getMain().getTempMax()));
    assertThat(weather.getTempCelsiusLow(), is(weatherEntity.getMain().getTempMin()));
  }

  private WeatherEntity createWeatherEntity(final String weatherJson) {
    Gson gson = new Gson();
    final Type weatherEntityType = new TypeToken<WeatherEntity>() {
    }.getType();
    return gson.fromJson(weatherJson, weatherEntityType);
  }
}
