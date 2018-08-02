package com.globoforce.mentoring.testautomation.uitesting.utils;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, ITestResult result) {
        if (!result.isSuccess()) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
