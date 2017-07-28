package com.fernandocejas.android10.sample.data.entity.mapper;

import com.fernandocejas.android10.sample.data.entity.WeatherEntity;
import com.fernandocejas.android10.sample.domain.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by Ruby on 7/27/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class WeatherEntityDataMapperTest {
  private static final String WEATHER_JSON = "{\n"
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

  private WeatherEntityDataMapper weatherEntityDataMapper;

  @Before public void setUp() throws Exception {
    weatherEntityDataMapper = new WeatherEntityDataMapper();
  }

  //todo test null input?
  @Test public void testTransformWeatherEntityEntity() {
    WeatherEntity weatherEntity = createWeatherEntity();
    Weather weather = weatherEntityDataMapper.transform(weatherEntity);

    assertThat(weather, is(instanceOf(Weather.class)));
    assertThat(weather.getMain(), is(weatherEntity.getWeather().get(0).getMain()));
    assertThat(weather.getDescription(), is(weatherEntity.getWeather().get(0).getDescription()));
    assertThat(weather.getIcon(), is(weatherEntity.getWeather().get(0).getIcon()));
    assertThat(weather.getName(), is(weatherEntity.getName()));
    assertThat(weather.getTempCelsius(), is(weatherEntity.getMain().getTemp()));
    assertThat(weather.getTempCelsiusHigh(), is(weatherEntity.getMain().getTempMax()));
    assertThat(weather.getTempCelsiusLow(), is(weatherEntity.getMain().getTempMin()));
  }

  private WeatherEntity createWeatherEntity() {
    Gson gson = new Gson();
    final Type weatherEntityType = new TypeToken<WeatherEntity>() {
    }.getType();
    return gson.fromJson(WEATHER_JSON, weatherEntityType);
  }
}
