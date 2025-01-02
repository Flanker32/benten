package utils;

import com.intuit.benten.hackernews.exceptions.BentenHackernewsException;
import com.intuit.benten.hackernews.properties.HackernewsProperties;
import com.intuit.benten.hackernews.utils.FetchHackernewsItemTask;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ActiveProfiles("mock")
@SpringJUnitConfig(classes = {HackernewsClientMockConfig.class})
@EnableAutoConfiguration
public class FetchHackernewsItemTaskTest {
    @Autowired
    HackernewsProperties hackernewsProperties;

    private FetchHackernewsItemTask buildFetchTask() {
        return new FetchHackernewsItemTask(hackernewsProperties.getUrlWithApiVersion(), 1);
    }

    private FetchHackernewsItemTask buildFailureFetchTask() {
        return new FetchHackernewsItemTask(hackernewsProperties.getBaseUrl(), 1);
    }

    // Successful hackernews api response
    @Test
    public void testFetchTaskCall() {
        FetchHackernewsItemTask task = buildFetchTask();
        HttpResponse response = task.call();
        Assertions.assertEquals(response.getCode(), 200);
    }

    // Failed hackernews api response
    @Test
    public void testFailedFetchTaskCall() {
        FetchHackernewsItemTask task = buildFailureFetchTask();
        try {
            task.call();
        } catch (BentenHackernewsException e) {
            Assertions.assertNotNull(e.getMessage());
        }
    }

    // Invalid hackernews api response
    @Test
    public void testInvalidFetchTaskCall() {
        FetchHackernewsItemTask task = new FetchHackernewsItemTask(hackernewsProperties.getBaseUrl(), null);
        try {
            task.call();
        } catch (BentenHackernewsException e) {
            Assertions.assertNotNull(e.getMessage());
        }
    }
}
