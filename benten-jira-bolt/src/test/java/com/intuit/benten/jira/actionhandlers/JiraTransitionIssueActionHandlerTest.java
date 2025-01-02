package com.intuit.benten.jira.actionhandlers;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.intuit.benten.common.actionhandlers.BentenHandlerResponse;
import com.intuit.benten.jira.helpers.MessageBuilder;
import com.intuit.benten.jira.BentenJiraClient;
import com.intuit.benten.jira.helpers.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author Divakar Ungatla
 * @version 1.0
 */

@EnableAutoConfiguration
public class JiraTransitionIssueActionHandlerTest extends JiraActionHandlerTest{
    @Autowired
    JiraTransitionIssueActionHandler jiraTransitionIssueActionHandler;

    @Autowired
    BentenJiraClient bentenJiraClient;

    @Test
    public void testHandleRequest() throws InterruptedException {

        String issueKey = TestHelper.createStory(bentenJiraClient);
        TestHelper.updateAssigneeAndReporter(bentenJiraClient,issueKey);
        JsonElement status = new JsonPrimitive("Closed");
        JsonElement currentuser = new JsonPrimitive(TestHelper.assigneeName);

        Thread.sleep(500);

        BentenHandlerResponse bentenHandlerResponse =
                jiraTransitionIssueActionHandler.handle(MessageBuilder.constructBentenTransitionIssueMessage(new JsonPrimitive(issueKey),status,currentuser));

        Thread.sleep(500);

        Assertions.assertTrue(TestHelper.getIssueDetails(bentenJiraClient,issueKey).getStatus().getName().equals("Closed"));
        Assertions.assertNotNull(bentenHandlerResponse.getBentenSlackResponse());

    }
    @Test
    public void testHandleRequestInvalidIssue() throws InterruptedException {

        String issueKey = TestHelper.createStory(bentenJiraClient);
        TestHelper.updateAssigneeAndReporter(bentenJiraClient,issueKey);
        JsonElement status = new JsonPrimitive("Closed");
        JsonElement currentuser = new JsonPrimitive(TestHelper.assigneeName);

        Thread.sleep(500);

        BentenHandlerResponse bentenHandlerResponse =
                jiraTransitionIssueActionHandler.handle(MessageBuilder.constructBentenTransitionIssueMessage(new JsonPrimitive("INVALID-1"),status,currentuser));

        Thread.sleep(1000);

        Assertions.assertNotNull(bentenHandlerResponse.getBentenSlackResponse());
        Assertions.assertNotNull(bentenHandlerResponse.getBentenSlackResponse().getSlackText());
        Assertions.assertTrue(bentenHandlerResponse.getBentenSlackResponse().getSlackText().contains("Issue Does Not Exist"));

    }


}
