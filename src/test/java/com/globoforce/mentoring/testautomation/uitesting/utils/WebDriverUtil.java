package com.globoforce.mentoring.testautomation.uitesting.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
                if (driver == null) {
                    System.setProperty("webdriver.chrome.driver", this.path);
                    driver = new ChromeDriver();
                }
                break;
            case "firefox":
                if (driver == null) {
                    System.setProperty("webdriver.gecko.driver", this.path);
                    driver = new FirefoxDriver();
                }
                break;
            case "edge":
                if (driver == null) {
                    System.setProperty("webdriver.edge.driver", this.path);
                    driver = new EdgeDriver();
                }
                break;
            default:
                if (driver == null) {
                    System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
                    driver = new ChromeDriver();
                }
                break;
        };
        return driver;
    }
}
