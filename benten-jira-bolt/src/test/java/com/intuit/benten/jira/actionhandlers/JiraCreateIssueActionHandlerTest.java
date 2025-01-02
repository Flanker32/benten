package com.intuit.benten.jira.actionhandlers;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.intuit.benten.jira.JiraClientMockConfig;
import com.intuit.benten.common.actionhandlers.BentenHandlerResponse;
import com.intuit.benten.jira.helpers.MessageBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JiraCreateIssueActionHandlerTest extends JiraActionHandlerTest {

    @Autowired
    JiraCreateIssueActionHandler jiraCreateIssueActionHandler;

    @Test
    public void testHandleRequest(){

        JsonElement projectKey = new JsonPrimitive("PSAA");
        JsonElement userOfInterest = new JsonPrimitive("dungatla");
        JsonElement currentUser = new JsonPrimitive("dungatla");
        JsonElement issueType = new JsonPrimitive("Story");
        JsonElement summary = new JsonPrimitive("Summary");


        BentenHandlerResponse bentenHandlerResponse =
                jiraCreateIssueActionHandler.handle(MessageBuilder.constructBentenCreateIssueMessage(projectKey,currentUser,userOfInterest,summary,issueType));

        Assertions.assertNotNull(bentenHandlerResponse.getBentenSlackResponse());
        Assertions.assertNotNull(bentenHandlerResponse.getBentenSlackResponse().getSlackText());

    }


}
