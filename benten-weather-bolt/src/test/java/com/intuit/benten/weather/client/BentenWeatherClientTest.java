package com.intuit.benten.weather.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
@ComponentScan(value = "com.intuit.benten")
public class BentenWeatherClientTest {

  @Autowired
  BentenWeatherClient bentenWeatherClient;

  @Test
  public void testInvalidApiKey() throws IOException {
    String foo = bentenWeatherClient.getWeatherFromCurrentCity("foo");
    JsonObject object = (JsonObject) new JsonParser().parse(foo);
    Assertions.assertEquals(401, object.getAsJsonPrimitive("cod").getAsInt());
  }

}
