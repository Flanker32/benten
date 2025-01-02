package com.intuit.benten.rest;

import com.intuit.benten.common.http.HttpHelper;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Divakar Ungatla
 * @version 1.0
 */
@RestController
public class MockController {

    @Autowired
    HttpHelper httpHelper;

    @RequestMapping("/browse/{id}")
    public String returnIssueFromMock(@PathVariable String id) throws IOException {
        HttpGet httpGet = new HttpGet("http://localhost:8001/browse/"+id);
        HttpResponse httpResponse = httpHelper.getClient().execute(httpGet);
        String response = EntityUtils.toString(httpResponse.getEntity());
        return response;
    }
}
