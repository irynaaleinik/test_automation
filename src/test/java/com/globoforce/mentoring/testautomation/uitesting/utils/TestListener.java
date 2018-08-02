package com.globoforce.mentoring.testautomation.uitesting.utils;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    Logger logger = Logger.getLogger(getClass());

    public void onTestStart(ITestResult result) {
        logger.info("Test '" + result.getName()+ "' STARTED");
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("Test '" + result.getName() + "' PASSED");
    }

    public void onTestFailure(ITestResult result) {
        logger.info("Test '" + result.getName() + "' FAILED");
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("Test '" + result.getName() + "' SKIPPED");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("Test method '" + result.getName() + "' correspond threshold of success rate");
    }

    public void onStart(ITestContext iTestContext) {
        logger.info("Test class '" + iTestContext.getName()+ "' STARTED");
    }

    public void onFinish(ITestContext iTestContext) {
        logger.info("Test class '" + iTestContext.getName()+  "' FINISHED");
    }
}
