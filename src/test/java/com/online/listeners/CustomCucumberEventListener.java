package com.online.listeners;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;

import java.util.ArrayList;
import java.util.List;


public class CustomCucumberEventListener implements EventListener {

    private List<TestCase> testCases = new ArrayList<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
        publisher.registerHandlerFor(TestStepStarted.class, this::handleTestStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        System.out.println("Test Scenario : " + event.getTestCase().getName());
        ExtentManager.createTest("Test Scenario : " + event.getTestCase().getName())
        .log(com.aventstack.extentreports.Status.INFO, event.getTestCase().getName());
        testCases.add(event.getTestCase());
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        System.out.println("Test Scenario Finished: " + event.getTestCase().getName() +
                           " with status " + event.getResult().getStatus());
        Status status =event.getResult().getStatus();
//        if(status.is(Status.PASSED)) {
//        	 ExtentManager.createTest("Test Scenario Finished: " + event.getTestCase().getName())
//             .log(com.aventstack.extentreports.Status.PASS, event.getTestCase().getName());
//        }else if(status.is(Status.FAILED)) {
//        	ExtentManager.createTest("Test Scenario Finished: " + event.getTestCase().getName())
//            .log(com.aventstack.extentreports.Status.FAIL, event.getTestCase().getName());
//        }
       
        testCases.add(event.getTestCase());
    }

    private void handleTestStepStarted(TestStepStarted event) {
        System.out.println("Test Step Started: " + event.getTestStep().getCodeLocation());
//        ExtentManager.createTest("Test Step Started: " + event.getTestStep().getCodeLocation())
//        .log(com.aventstack.extentreports.Status.INFO, event.getTestCase().getName());
        ExtentManager.logStep("Test Step Started: " + event.getTestStep().getCodeLocation());
    }

    private void handleTestStepFinished(TestStepFinished event) {
        System.out.println("Test Step Finished: " + event.getTestStep().getCodeLocation() +
                           " with status " + event.getResult().getStatus());
        
        Status status =event.getResult().getStatus();
        if(status.is(Status.PASSED)) {
        	ExtentManager.logStepPass("Test Step Passed: \n" + event.getTestStep().getCodeLocation());
            
       }else if(status.is(Status.FAILED)) {
    	  String error= event.getResult().getError().toString();
    	   ExtentManager.logStepfail("Test Step Failed: \n" + event.getTestStep().getCodeLocation()+"\n"+ error);

//                ExtentManager.getTest().fail("step failed", MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentLogger.base64Image()).build());

//    	   ExtentManager.logStepfail(error);
    	   
       }else if(status.is(Status.SKIPPED)) {
    	   
    	   String skip= event.getResult().getStatus().toString();
    	   ExtentManager.logStepSkip("Test Step skip: \n" + event.getTestStep().getCodeLocation()+"\n"+ skip);
       }
       
    }
}

