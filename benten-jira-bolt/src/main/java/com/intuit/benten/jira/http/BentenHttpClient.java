package com.intuit.benten.jira.http;

import com.intuit.benten.common.http.HttpHelper;
import com.intuit.benten.jira.properties.JiraProperties;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.client5.http.auth.Credentials;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpRequestBase;
import org.apache.hc.client5.http.impl.auth.BasicScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Divakar Ungatla
 * @version 1.0
 */
@Component
public class BentenHttpClient {

    @Autowired
    private HttpHelper httpHelper;

    @Autowired
    private JiraProperties jiraProperties;

    protected HttpResponse request(HttpRequestBase req) throws IOException {
        req.addHeader("Accept", "application/json");
        req.addHeader("Connection", "close");
        Credentials creds = new UsernamePasswordCredentials(jiraProperties.getUsername(),jiraProperties.getPassword());
        req.addHeader(BasicScheme.authenticate(creds, "utf-8", false));
        HttpResponse httpResponse = this.httpHelper.getClient().execute(req);
        return httpResponse;
    }
}
