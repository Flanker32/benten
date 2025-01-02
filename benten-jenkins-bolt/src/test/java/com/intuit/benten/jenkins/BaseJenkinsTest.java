package com.intuit.benten.jenkins;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author Divakar Ungatla
 * @version 1.0
 */
@ActiveProfiles("mock")
@SpringJUnitConfig(classes = {JenkinsClientMockConfig.class})
@EnableAutoConfiguration
public class BaseJenkinsTest {

    @Test
    public void test(){}
}
