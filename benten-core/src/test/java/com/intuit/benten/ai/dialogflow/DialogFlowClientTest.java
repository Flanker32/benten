package com.intuit.benten.ai.dialogflow;

//import BentenMessage;
import com.intuit.benten.nlp.NlpClient;
import com.intuit.benten.nlp.dialogflow.DialogFlowClient;
import com.intuit.benten.properties.BentenProxyConfig;
import com.intuit.benten.properties.AiProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Divakar Ungatla
 * @version 1.0
 */
@SpringBootTest(classes={NlpClient.class, BentenProxyConfig.class, AiProperties.class})
public class DialogFlowClientTest {

    private NlpClient nlpClient;

    @Autowired
    private BentenProxyConfig bentenProxyConfig;

    @BeforeEach
    public void setup() {
        nlpClient = new DialogFlowClient("project-id");
    }

    @Test
    public void sendAMessageToNlpClient()  {
//        BentenMessage bentenMessage = nlpClient.sendText("Book a ticket from Delhi to Bangalore on feb 20 th", "test");
//       // Assert.assertEquals(bentenMessage.getAction(),"smalltalk.greetings.hello");
//        Assert.assertEquals(bentenMessage.isActionComplete(),true);
    }

 //   @Test(expected = AiException.class)
    public void sendAMessageToNlpClientFalseAuth()  {
//        NlpClient nlpClient = new DialogFlowClient("invalid-token",bentenProxyConfig);
//        BentenMessage bentenMessage = nlpClient.sendText("hello", "test");
//        Assert.assertEquals(bentenMessage.getAction(),"smalltalk.greetings.hello");
//        Assert.assertEquals(bentenMessage.isActionComplete(),true);
    }

}
