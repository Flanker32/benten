package com.intuit.benten.jenkins;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intuit.benten.BentenJenkinsClient;
import com.intuit.benten.jenkins.model.JenkinsJobBuildParameter;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.List;


/**
 * Created by dungatla.
 */

@EnableAutoConfiguration
public class BentenJenkinsClientTest extends BaseJenkinsTest{

    @Autowired
    BentenJenkinsClient bentenJenkinsClient;

    @Test
    public void testJenkinsServerConnection(){
        Assertions.assertTrue(bentenJenkinsClient.getJenkins().isRunning());
    }

    @Test
    public void testGetJobByJobName() throws JsonProcessingException {
        String jobName = "BenTen-Env-Stability";
        JobWithDetails jobWithDetails = bentenJenkinsClient.getJobByJobName(jobName);
        String jobNameFromJenkins = jobWithDetails.getDisplayName();
        Assertions.assertEquals(jobName, jobNameFromJenkins, "Build Names dont match");
    }

    @Test
    public void testGetAllJobsWithPrefix(){
        Assertions.assertNotNull(bentenJenkinsClient.getAllJobsWithPrefix("BenTen"));
    }

    @Test
    public void testBuildJob(){
        String jobName = "BenTen-Env-Stability";
        Assertions.assertNotNull(bentenJenkinsClient.build(jobName));
    }

    @Test
    @Disabled
    public void testConsoleOutputForBuild(){
        String jobName = "BenTen-Env-Stability";
        int buildNumber = 344;
        Assertions.assertNotNull(bentenJenkinsClient.showConsoleLogForJobWithBuildNumber(jobName,buildNumber));
    }

    @Test
    public void testGetBuildParams(){
        String jobName = "A-release";

        List<JenkinsJobBuildParameter> a = bentenJenkinsClient.getBuildParams(jobName);
        Assertions.assertTrue(a.size()>0);
        a.forEach(item -> {
            System.out.println("Name: " + item.getName());
            System.out.println("Default Value: " + item.getDefaultValue());
            System.out.println("Choices: " + item.getChoices());
        });

        System.out.println("---------------------------------------------------------------------------------------------------");

    }
}
