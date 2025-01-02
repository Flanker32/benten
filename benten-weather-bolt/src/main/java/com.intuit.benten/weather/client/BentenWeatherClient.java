package com.intuit.benten.weather.client;

import com.intuit.benten.common.http.HttpHelper;
import com.intuit.benten.weather.properties.WeatherProperties;
import java.io.IOException;
import java.net.URLEncoder;
import jakarta.annotation.PostConstruct;
import org.apache.hc.core5.http.HttpEntityContainer;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BentenWeatherClient {

  @Autowired
  private WeatherProperties weatherProperties;

  @Autowired
  private HttpHelper httpHelper;

  private String url;

  @PostConstruct
  public void setup() {
    url = weatherProperties.getBaseUrl() + weatherProperties.getToken();
  }

  public String getWeatherFromCurrentCity(String city) throws IOException {
    HttpGet httpGet = new HttpGet(url + "&q=" + URLEncoder.encode(city,"UTF-8"));
    HttpResponse httpResponse = httpHelper.getClient().execute(httpGet);
      try {
          return httpResponse instanceof HttpEntityContainer ?
              EntityUtils.toString(((HttpEntityContainer) httpResponse).getEntity()) : "";
      } catch (ParseException e) {
          throw new IOException(e);
      }
  }


}
