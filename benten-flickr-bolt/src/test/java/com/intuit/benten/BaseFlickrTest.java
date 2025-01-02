package com.intuit.benten;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


/**
 * Created by itstc on 2019-10-15
 */
@ActiveProfiles("mock")
@SpringJUnitConfig(classes = {FlickrClientMockConfig.class})
@EnableAutoConfiguration
public class BaseFlickrTest {

    @Test
    public void test(){}
}
