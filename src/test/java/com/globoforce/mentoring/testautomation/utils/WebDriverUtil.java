package com.globoforce.mentoring.testautomation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.io.File;

public class WebDriverUtil {
    private String driverName;
    private String path;
    WebDriver driver;

    public WebDriverUtil(String driverName, String path){
        this.driverName = driverName;
        this.path = path;

    }

    public WebDriver setDriver() {
        switch (this.driverName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", this.path);
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", this.path);
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", this.path);
                driver = new EdgeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
        };
        return driver;
    }
}
