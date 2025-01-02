package com.intuit.benten.jira.conversationcatalysts;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.intuit.benten.jira.JiraClientMockConfig;
import com.intuit.benten.common.bentennlp.Conversation;
import com.intuit.benten.jira.helpers.MessageBuilder;
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
public class JiraCreateIssueConversationCatalystTest{

     @Autowired
     JiraCreateIssueConversationCatalyst jiraCreateIssueConversationCatalyst;

     @Test
     public void testHandleRequest(){

          JsonElement projectKey = new JsonPrimitive("PSAA");
          JsonElement issueType = new JsonPrimitive("Story");

          Conversation conversation =
                  jiraCreateIssueConversationCatalyst.getConversation(MessageBuilder.constructBentenCreateIssueCatalystMessage(projectKey,issueType));
     }
}
