package com.intuit.benten.weather.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeatherResponseUtilTest {


  @Test
  public void checkWithNullResponse(){
    Assertions.assertTrue(WeatherResponseUtil.format(null).contains(WeatherResponseUtil.EERROR_MESSAGE));
    Assertions.assertTrue(WeatherResponseUtil.format("").contains(WeatherResponseUtil.EERROR_MESSAGE));
    Assertions.assertTrue(WeatherResponseUtil.format("fdjksfdkj").contains(WeatherResponseUtil.EERROR_MESSAGE));

  }

  @Test
  public void check200Response(){
    Assertions.assertTrue(WeatherResponseUtil.format("{'cod': 201}").contains(WeatherResponseUtil.EERROR_MESSAGE));
    Assertions.assertTrue(WeatherResponseUtil.format("{'code': 201}").contains(WeatherResponseUtil.EERROR_MESSAGE));
    Assertions.assertFalse(WeatherResponseUtil.format("{'cod': 200}").contains(WeatherResponseUtil.EERROR_MESSAGE));
  }

  @Test
  public void checkTemperatureResponse(){
    String response = WeatherResponseUtil.format("{'cod':200, 'main': {'temp': '21'}}");
    Assertions.assertTrue(response.contains(WeatherResponseUtil.TEMPERATURE_FORMAT.formatted("21")));
    Assertions.assertTrue(response.contains("No wind".formatted()));
    Assertions.assertTrue(response.contains("No clouds".formatted()));
    Assertions.assertTrue(response.contains("No snow".formatted()));
    Assertions.assertTrue(response.contains("No rain".formatted()));
  }

  @Test
  public void checkPressureResponse(){
    String response = WeatherResponseUtil.format("{'cod':200, 'main': {'pressure': '21'}}");
    Assertions.assertTrue(response.contains(WeatherResponseUtil.PRESSURE_FORMAT.formatted("21")));
    Assertions.assertTrue(response.contains("No wind".formatted()));
    Assertions.assertTrue(response.contains("No clouds".formatted()));
    Assertions.assertTrue(response.contains("No snow".formatted()));
    Assertions.assertTrue(response.contains("No rain".formatted()));
  }

  @Test
  public void checkHumidityResponse(){
    String response = WeatherResponseUtil.format("{'cod':200, 'main': {'humidity': '21'}}");
    Assertions.assertTrue(response.contains(WeatherResponseUtil.HUMIDITY_FORMAT.formatted("21")));
    Assertions.assertTrue(response.contains("No wind".formatted()));
    Assertions.assertTrue(response.contains("No clouds".formatted()));
    Assertions.assertTrue(response.contains("No snow".formatted()));
    Assertions.assertTrue(response.contains("No rain".formatted()));
  }



  @Test
  public void checkWindResponse(){
    String response = WeatherResponseUtil.format("{'cod':200, 'wind': {'speed': '2000'}}");
    Assertions.assertTrue(response.contains(WeatherResponseUtil.WIND_FORMAT.formatted("2000")));
    Assertions.assertTrue(response.contains("No clouds".formatted()));
    Assertions.assertTrue(response.contains("No snow".formatted()));
    Assertions.assertTrue(response.contains("No rain".formatted()));
  }

  @Test
  public void checkCloudResponse(){
    String response = WeatherResponseUtil.format("{'cod':200, 'clouds': {'all': '2000'}}");
    Assertions.assertTrue(response.contains(WeatherResponseUtil.CLOUD_FORMAT.formatted("2000")));
    Assertions.assertTrue(response.contains("No wind".formatted()));
    Assertions.assertTrue(response.contains("No snow".formatted()));
    Assertions.assertTrue(response.contains("No rain".formatted()));
  }

  @Test
  public void checkSnowResponse(){
    String response = WeatherResponseUtil.format("{'cod':200, 'snow': {'1h': '20'}}");
    Assertions.assertTrue(response.contains(WeatherResponseUtil.SNOW_FORMAT.formatted("20")));
    Assertions.assertTrue(response.contains("No clouds".formatted()));
    Assertions.assertTrue(response.contains("No wind".formatted()));
    Assertions.assertTrue(response.contains("No rain".formatted()));
  }

  @Test
  public void checkRainResponse(){
    String response = WeatherResponseUtil.format("{'cod':200, 'rain': {'1h': '2000'}}");
    Assertions.assertTrue(response.contains(WeatherResponseUtil.RAIN_FORMAT.formatted("2000")));
    Assertions.assertTrue(response.contains("No clouds".formatted()));
    Assertions.assertTrue(response.contains("No snow".formatted()));
    Assertions.assertTrue(response.contains("No wind".formatted()));
  }

}
