package com.example;

import com.intuit.benten.common.actionhandlers.BentenActionHandler;
import com.intuit.benten.common.actionhandlers.BentenHandlerResponse;
import com.intuit.benten.common.actionhandlers.BentenSlackResponse;
import com.intuit.benten.common.annotations.ActionHandler;
import com.intuit.benten.common.formatters.SlackFormatter;
import com.intuit.benten.common.helpers.BentenMessageHelper;
import com.intuit.benten.common.http.HttpHelper;
import com.intuit.benten.common.nlp.BentenMessage;
import org.apache.hc.core5.http.HttpEntityContainer;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Divakar Ungatla
 * @version 1.0
 */
@Component
@ActionHandler(action = "action_benten_example")
public class BentenExampleAction implements BentenActionHandler {

    @Autowired
    HttpHelper httpHelper;

    @Override
    public BentenHandlerResponse handle(BentenMessage bentenMessage) {

        String application = BentenMessageHelper.getParameterAsString(bentenMessage, "application");
        Integer noOfPings = BentenMessageHelper.getParameterAsInteger(bentenMessage, "noOfIntegers");

        BentenHandlerResponse bentenHandlerResponse = new BentenHandlerResponse();
        BentenSlackResponse bentenSlackResponse = new BentenSlackResponse();
        bentenHandlerResponse.setBentenSlackResponse(bentenSlackResponse);

        try {
            for (int counter = 0; counter < noOfPings; counter++) {

                HttpGet httpGet = new HttpGet("https://bentenapp.herokuapp.com/health");
                HttpResponse httpResponse = httpHelper.getClient().execute(httpGet);
                String response = httpResponse instanceof HttpEntityContainer ?
                        EntityUtils.toString(((HttpEntityContainer) httpResponse).getEntity()) : "";

                Thread.sleep(1000);

                String responseToSend = SlackFormatter.create().text("Pinged ").code(application).text(" ")
                        .code(String.valueOf(counter+1)).text(" times. Below is the response")
                        .newline().preformatted(response)
                        .build();

                bentenSlackResponse.setSlackText(responseToSend);
                bentenMessage.getChannel().sendMessage(bentenHandlerResponse,bentenMessage.getChannelInformation());
            }

        } catch (Exception ex) {
            // not handled
        }

        bentenSlackResponse.setSlackText("`Ok I completed what you asked me to do`. Pinged "
                +application+" "+noOfPings+ " `times.`" );

        return bentenHandlerResponse;
    }
}
