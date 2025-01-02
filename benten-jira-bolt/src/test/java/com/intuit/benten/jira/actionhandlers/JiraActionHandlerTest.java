package com.intuit.benten.jira.actionhandlers;

import com.intuit.benten.jira.JiraClientMockConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author Divakar Ungatla
 * @version 1.0
 */
@EnableAutoConfiguration
//@SpringBootTest
@ActiveProfiles("mock")
@SpringJUnitConfig(classes = JiraClientMockConfig.class)
public class JiraActionHandlerTest {

    @Test
    public void dummy(){}
}
